package kr.paysync.sample.backend.cashreceipt.dto

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType

class IssueCashReceiptRequest(
    val type: CashReceiptType,
    val identifier: String,
    val amount: Int
)