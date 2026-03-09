package kr.paysync.sample.backend.cashreceipt.service

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt
import kr.paysync.sample.backend.paysync.dto.ApiResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class CashReceiptServiceImpl(
    @Qualifier("paySyncRestClient")
    private val restClient: RestClient
) : CashReceiptService {

    override fun issue(request: IssueCashReceiptRequest): CashReceipt {
        val response = restClient.post()
            .uri("/v1/cash-receipts")
            .body(request)
            .retrieve()
            .body<ApiResponse<CashReceipt>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        when (response.code) {
            "CREATED" -> throw IllegalStateException("현금영수증 발행에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun retrieveById(id: String): CashReceipt {
        val response = restClient.get()
            .uri("/v1/cash-receipts/{id}", id)
            .retrieve()
            .body<ApiResponse<CashReceipt>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("현금영수증 조회에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun revokeById(id: String) {
        val response = restClient.post()
            .uri("/v1/cash-receipts/{id}/revoke", id)
            .retrieve()
            .body<ApiResponse<Unit>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("현금영수증 취소발행에 실패했습니다: ${response.code}")
        }
    }
}