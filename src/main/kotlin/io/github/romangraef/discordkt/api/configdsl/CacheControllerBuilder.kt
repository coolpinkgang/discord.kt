package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.cache.CachePolicy
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlin.reflect.KClass

@DiscordKtDsl
class CacheControllerBuilder {
    private val policies: Map<KClass<out ApiModel>, CachePolicyBuilder<out ApiModel>> =
        ALL_CACHED_OBJECTS.map { (modelClass, _) -> modelClass to CachePolicyBuilder<ApiModel>() }.toMap()

    fun <T : ApiModel> getPolicyBuilderFor(kClass: KClass<T>) =
        policies[kClass] as CachePolicyBuilder<T>

    inline fun <reified T : ApiModel> getPolicyBuilderFor() =
        getPolicyBuilderFor(T::class)

    inline fun <reified T : ApiModel> policy(noinline builder: CachePolicyBuilder<T>.() -> Unit) {
        getPolicyBuilderFor<T>().run(builder)
    }

    companion object {
        // Second star is for the transformer. Actual signature: (id: BaseSnowflake, discordKt: DiscordKt) -> V
        private val ALL_CACHED_OBJECTS =
            mutableMapOf<KClass<out ApiModel>, Pair<*, KClass<out BaseSnowflake>>>()

        fun <V : ApiModel> addCachedObject(
            kClass: KClass<V>,
            transformer: (id: Snowflake, discordKt: DiscordKt) -> V,
            snowflakeClass: KClass<out BaseSnowflake>
        ) {
            ALL_CACHED_OBJECTS[kClass] = transformer to snowflakeClass
        }
        init{
            addCachedObject(User::class, ::User, io.github.romangraef.discordkt.models.user.User::class)
        }

        fun <V : ApiModel> createCacheForPolicy(policy: CachePolicy<V>, kClass: KClass<out V>): PartialCache<out V>? =
            ALL_CACHED_OBJECTS[kClass]?.let { (transformer, snowflakeClass) ->
                PartialCache<V>(
                    policy,
                    kClass,
                    snowflakeClass,
                    transformer as (id: Snowflake, discordKt: DiscordKt) -> V
                )
            }

    }

    fun build(): List<PartialCache<out ApiModel>> =
        policies.map { (modelClass, policy) -> createCacheForPolicy(policy.build(), modelClass as KClass<Nothing>) }
            .filterNotNull()
}
