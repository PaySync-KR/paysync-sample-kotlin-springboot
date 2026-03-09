package kr.paysync.sample.backend.bankaccount.model

import kr.paysync.sample.backend.bankaccount.enums.BankAccountProvider
import kr.paysync.sample.backend.bankaccount.enums.BankAccountType
import java.util.Date

class BankAccount(
    val id: String,
    val active: Boolean,
    val ownerId: String,
    val alias: String,
    val provider: BankAccountProvider,
    val type: BankAccountType,
    val number: String,
    val createdAt: Date,
    val verifiedAt: Date?
)