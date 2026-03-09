package kr.paysync.sample.backend.invoice.service

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest
import kr.paysync.sample.backend.invoice.model.Invoice

interface InvoiceService {

    fun issue(request: IssueInvoiceRequest): Invoice

    fun retrieveAll(
        offset: Long,
        limit: Int,
        paid: Boolean? = null,
        dateAfter: String? = null,
        dateBefore: String? = null
    ): List<Invoice>

    fun retrieveById(id: String): Invoice

    fun markAsPaid(id: String): Invoice

    fun deleteById(id: String)
}