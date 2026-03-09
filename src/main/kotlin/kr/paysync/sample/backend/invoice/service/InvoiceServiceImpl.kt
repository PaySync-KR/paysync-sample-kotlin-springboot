package kr.paysync.sample.backend.invoice.service

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest
import kr.paysync.sample.backend.invoice.model.Invoice
import kr.paysync.sample.backend.paysync.dto.ApiResponse
import kr.paysync.sample.backend.paysync.dto.PaginatedApiResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.util.Optional

@Service
class InvoiceServiceImpl(
    @Qualifier("paySyncRestClient")
    private val restClient: RestClient
) : InvoiceService {

    override fun issue(request: IssueInvoiceRequest): Invoice {
        val response = restClient.post()
            .uri("/v1/invoices")
            .body(request)
            .retrieve()
            .body<ApiResponse<Invoice>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "CREATED") {
            throw IllegalStateException("주문 생성에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun retrieveAll(
        offset: Long,
        limit: Int,
        paid: Boolean?,
        dateAfter: String?,
        dateBefore: String?
    ): List<Invoice> {
        val response = restClient.get()
            .uri {
                it.path("/v1/invoices")
                    .queryParam("offset", offset)
                    .queryParam("limit", limit)
                    .queryParamIfPresent("paid", Optional.ofNullable(paid))
                    .queryParamIfPresent("dateAfter", Optional.ofNullable(dateAfter))
                    .queryParamIfPresent("dateBefore", Optional.ofNullable(dateBefore))
                    .build()
            }
            .retrieve()
            .body<PaginatedApiResponse<Invoice>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("주문 조회에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun retrieveById(id: String): Invoice {
        val response = restClient.get()
            .uri("/v1/invoices/{id}", id)
            .retrieve()
            .body<ApiResponse<Invoice>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("주문 조회에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun markAsPaid(id: String): Invoice {
        val response = restClient.post()
            .uri("/v1/invoices/{id}/mark-as-paid", id)
            .retrieve()
            .body<ApiResponse<Invoice>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("주문 수동 승인에 실패했습니다: ${response.code}")
        }

        return response.data
    }

    override fun deleteById(id: String) {
        val response = restClient.delete()
            .uri("/v1/invoices/{id}", id)
            .retrieve()
            .body<ApiResponse<Void>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "DELETED") {
            throw IllegalStateException("주문 삭제에 실패했습니다: ${response.code}")
        }
    }
}