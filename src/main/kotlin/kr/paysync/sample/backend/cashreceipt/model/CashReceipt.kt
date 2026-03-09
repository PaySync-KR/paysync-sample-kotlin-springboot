package kr.paysync.sample.backend.cashreceipt.model

import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptStatus
import kr.paysync.sample.backend.cashreceipt.enums.CashReceiptType
import java.util.Date

class CashReceipt(
    val id: String,
    val status: CashReceiptStatus,
    val issuerId: String,
    val corpNum: String,
    val type: CashReceiptType,
    val identifier: String,
    val amount: Int,
    val relatedInvoiceId: String?,
    val ntsConfirmNum: String,
    val ntsResultCode: String?,
    val issuedAt: Date,
    val revokedAt: Date?
)