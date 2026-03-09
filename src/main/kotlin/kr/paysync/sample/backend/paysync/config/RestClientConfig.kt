package kr.paysync.sample.backend.paysync.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    @Bean
    fun paySyncRestClient(paySyncProperties: PaySyncProperties): RestClient {
        return RestClient.builder()
            .baseUrl(paySyncProperties.baseUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + paySyncProperties.apiKey)
            .build()
    }
}