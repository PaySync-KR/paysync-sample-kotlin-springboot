package kr.paysync.sample.backend.invoice.dto

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType

class IssueInvoiceRequest(
    val customer: Customer,
    val amount: Int,
    val cashReceipt: CashReceipt?,
    val expireAfter: String?,
    val metadata: Map<String, String>?
) {

    class Customer(
        val name: String,
        val email: String?,
        val phoneNumber: String?
    )

    class CashReceipt(
        val type: CashReceiptType,
        val identifier: String
    )
}