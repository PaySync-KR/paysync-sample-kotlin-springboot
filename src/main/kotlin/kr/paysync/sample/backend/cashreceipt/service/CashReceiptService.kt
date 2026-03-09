package kr.paysync.sample.backend.cashreceipt.service

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt

interface CashReceiptService {

    fun issue(request: IssueCashReceiptRequest): CashReceipt

    fun retrieveById(id: String): CashReceipt

    fun revokeById(id: String)
}