package kr.paysync.sample.backend.invoice.model

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType
import java.util.Date

class Invoice(
    val id: String,
    val issuerId: String,
    val customer: Customer,
    val cashReceipt: CashReceipt?,
    val amount: Int,
    val paid: Boolean,
    val metadata: Map<String, String>?,
    val issuedAt: Date,
    val expiresAt: Date?,
    val deletedAt: Date?
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