package kr.paysync.sample.backend.transaction.service

import kr.paysync.sample.backend.transaction.model.Transaction

interface TransactionService {

    fun retrieveAll(
        offset: Long,
        limit: Int,
        bankAccountId: String? = null,
        hidden: Boolean? = null,
        matched: Boolean? = null,
        dateAfter: String? = null,
        dateBefore: String? = null
    ): List<Transaction>

    fun retrieveById(id: String): Transaction
}