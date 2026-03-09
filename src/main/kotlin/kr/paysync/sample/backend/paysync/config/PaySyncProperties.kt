package kr.paysync.sample.backend.paysync.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("paysync")
class PaySyncProperties(
    val baseUrl: String,
    val apiKey: String,
    val webhookSigningSecret: String
)