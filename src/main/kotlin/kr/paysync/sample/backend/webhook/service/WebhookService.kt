package kr.paysync.sample.backend.webhook.service

interface WebhookService {

    fun handleWebhook(headers: Map<String, List<String>>, rawBody: String)
}