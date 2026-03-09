package kr.paysync.sample.backend.webhook.service

import com.standardwebhooks.Webhook
import io.github.oshai.kotlinlogging.KotlinLogging
import kr.paysync.sample.backend.common.config.PaySyncProperties
import org.springframework.stereotype.Service

@Service
class WebhookServiceImpl(
    properties: PaySyncProperties
) : WebhookService {

    private val log = KotlinLogging.logger {}
    private val webhook = Webhook(properties.webhookSigningSecret)

    override fun handleWebhook(headers: Map<String, List<String>>, rawBody: String) {
        try {
            webhook.verify(rawBody, headers)
        } catch (ex: Exception) {
            log.error(ex) {
                "웹훅 검증에 실패했습니다. headers: $headers, rawBody: $rawBody"
            }
        }

        log.info {
            "웹훅이 수신되었습니다: $rawBody"
        }
    }
}