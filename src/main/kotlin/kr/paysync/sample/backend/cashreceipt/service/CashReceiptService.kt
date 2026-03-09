package kr.paysync.sample.backend.cashreceipt.service

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest
import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptStatus
import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt
import kr.paysync.sample.backend.common.ManyResult

interface CashReceiptService {

    fun issue(request: IssueCashReceiptRequest): CashReceipt

    fun retrieveAll(
        offset: Long,
        limit: Int,
        status: CashReceiptStatus? = null,
        issuerId: String? = null,
        corpNum: String? = null,
        type: CashReceiptType? = null,
        identifier: String? = null,
        dateAfter: String? = null,
        dateBefore: String? = null
    ): ManyResult<CashReceipt>

    fun retrieveById(id: String): CashReceipt

    fun revokeById(id: String)
}