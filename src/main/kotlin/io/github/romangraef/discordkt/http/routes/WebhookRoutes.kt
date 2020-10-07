package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.webhook.Webhook
import io.github.romangraef.discordkt.models.webhook.WebhookCreate
import io.github.romangraef.discordkt.models.webhook.WebhookModify

object WebhookRoutes {
    fun CREATE_WEBHOOK(channelId: Snowflake) =
        POST<Webhook, WebhookCreate>("/channels/$channelId/webhooks")
    fun GET_CHANNEL_WEBHOOKS(channelId: Snowflake) =
        GET<List<Webhook>>("/channels/$channelId/webhooks")
    fun GET_WEBHOOK(webhookId: Snowflake) =
        GET<Webhook>("/webhooks/$webhookId")
    fun GET_WEBHOOK_WITH_TOKEN(webhookId: Snowflake, token: String) =
        GET<Webhook>("/webhooks/$webhookId/$token")
    fun MODIFY_WEBHOOK(webhookId: Snowflake) =
        PATCH<Webhook, WebhookModify>("/webhooks/$webhookId")
    fun DELETE_WEBHOOK(webhookId: Snowflake) =
        DELETE<Unit>("/webhooks/$webhookId")
    fun DELETE_WEBHOOK_WITH_TOKEN(webhookId: Snowflake, token: String) =
        DELETE<Unit>("/webhooks/$webhookId/$token")
}
