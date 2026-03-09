package kr.paysync.sample.backend.transaction.service

import kr.paysync.sample.backend.paysync.dto.ApiResponse
import kr.paysync.sample.backend.paysync.dto.PaginatedApiResponse
import kr.paysync.sample.backend.transaction.model.Transaction
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.util.Optional

@Service
class TransactionServiceImpl(
    @Qualifier("paySyncRestClient")
    private val restClient: RestClient
) : TransactionService {

    override fun retrieveAll(
        offset: Long,
        limit: Int,
        bankAccountId: String?,
        hidden: Boolean?,
        matched: Boolean?,
        dateAfter: String?,
        dateBefore: String?
    ): List<Transaction> {
        val response = restClient.get()
            .uri {
                it.path("/v1/transactions")
                    .queryParam("offset", offset)
                    .queryParam("limit", limit)
                    .queryParamIfPresent("bankAccountId", Optional.ofNullable(bankAccountId))
                    .queryParamIfPresent("hidden", Optional.ofNullable(hidden))
                    .queryParamIfPresent("matched", Optional.ofNullable(matched))
                    .queryParamIfPresent("dateAfter", Optional.ofNullable(dateAfter))
                    .queryParamIfPresent("dateBefore", Optional.ofNullable(dateBefore))
                    .build()
            }
            .retrieve()
            .body<PaginatedApiResponse<Transaction>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("입금 내역 조회 실패: ${response.code}")
        }

        return response.data
    }

    override fun retrieveById(id: String): Transaction {
        val response = restClient.get()
            .uri("/v1/transactions/{id}", id)
            .retrieve()
            .body<ApiResponse<Transaction>>()
            ?: throw IllegalStateException("올바르지 않은 응답입니다.")

        if (response.code != "OK") {
            throw IllegalStateException("입금 내역 조회 실패: ${response.code}")
        }

        return response.data
    }
}