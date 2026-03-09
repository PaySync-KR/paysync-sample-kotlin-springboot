package kr.paysync.sample.backend.transaction.model

import kr.paysync.sample.backend.bankaccount.model.BankAccount
import java.util.Date

class Transaction(
    val id: String,
    val bankAccount: BankAccount,
    val matchedInvoiceId: String?,
    val amount: Int,
    val description: String,
    val hidden: Boolean,
    val receivedAt: Date,
    val matchedAt: Date?,
    val createdAt: Date
)