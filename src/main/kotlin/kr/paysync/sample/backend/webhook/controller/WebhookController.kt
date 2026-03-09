package kr.paysync.sample.backend.webhook.controller

import kr.paysync.sample.backend.webhook.service.WebhookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/webhooks")
class WebhookController(
    private val webhookService: WebhookService
) {

    @PostMapping
    fun handleWebhook(
        @RequestHeader headers: Map<String, String>,
        @RequestBody rawBody: String
    ): ResponseEntity<Unit> {
        webhookService.handleWebhook(
            headers.mapValues { listOf(it.value) },
            rawBody
        )

        return ResponseEntity.ok().build()
    }
}