package dev.discordkt.api.configdsl

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.api.DiscordKt
import dev.discordkt.api.user.User
import dev.discordkt.cache.CachePolicy
import dev.discordkt.snowflake.BaseSnowflake
import dev.discordkt.snowflake.Snowflake

import kotlin.reflect.KClass

@DiscordKtDsl
class CacheControllerBuilder {
    private val policies: Map<KClass<out _root_ide_package_.dev.discordkt.api.ApiModel>, CachePolicyBuilder<out _root_ide_package_.dev.discordkt.api.ApiModel>> =
        ALL_CACHED_OBJECTS.map { (modelClass, _) -> modelClass to CachePolicyBuilder<_root_ide_package_.dev.discordkt.api.ApiModel>() }.toMap()

    fun <T : CacheableApiModel<out BaseSnowflake>> getPolicyBuilderFor(kClass: KClass<T>) =
        policies[kClass] as CachePolicyBuilder<T>

    inline fun <reified T : CacheableApiModel<out BaseSnowflake>> getPolicyBuilderFor() =
        getPolicyBuilderFor(T::class)

    inline fun <reified T : CacheableApiModel<out BaseSnowflake>> policy(noinline builder: CachePolicyBuilder<T>.() -> Unit) {
        getPolicyBuilderFor<T>().run(builder)
    }

    companion object {
        // Second star is for the transformer. Actual signature: (id: BaseSnowflake, discordKt: DiscordKt) -> V
        private val ALL_CACHED_OBJECTS =
            mutableMapOf<KClass<out _root_ide_package_.dev.discordkt.api.ApiModel>, Pair<*, KClass<out BaseSnowflake>>>()

        fun <V : _root_ide_package_.dev.discordkt.api.ApiModel> addCachedObject(
            kClass: KClass<V>,
            transformer: (id: Snowflake, discordKt: DiscordKt) -> V,
            snowflakeClass: KClass<out BaseSnowflake>
        ) {
            ALL_CACHED_OBJECTS[kClass] = transformer to snowflakeClass
        }
        init{
            addCachedObject(User::class, ::User, dev.discordkt.models.user.User::class)
        }

        fun <T : BaseSnowflake, V : CacheableApiModel<T>> createCacheForPolicy(policy: CachePolicy<V>, kClass: KClass<out V>): PartialCache<out T, out V>? =
            ALL_CACHED_OBJECTS[kClass]?.let { (transformer, snowflakeClass) ->
                PartialCache(
                    policy,
                    kClass,
                    snowflakeClass as KClass<out T>,
                    transformer as (id: Snowflake, discordKt: DiscordKt) -> V
                )
            }

    }

    fun build(): List<PartialCache<out BaseSnowflake, out CacheableApiModel<out BaseSnowflake>>> = TODO("roman will fix this")
        /*policies.map { (modelClass, policy) -> createCacheForPolicy(policy.build(), modelClass as KClass<Nothing>) }
            .filterNotNull()*/
}
