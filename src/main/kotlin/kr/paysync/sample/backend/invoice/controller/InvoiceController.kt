package kr.paysync.sample.backend.invoice.controller

import kr.paysync.sample.backend.invoice.dto.IssueInvoiceRequest
import kr.paysync.sample.backend.invoice.model.Invoice
import kr.paysync.sample.backend.invoice.service.InvoiceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/invoices")
class InvoiceController(
    private val service: InvoiceService
) {

    @PostMapping
    fun issue(
        @RequestBody body: IssueInvoiceRequest
    ): ResponseEntity<Invoice> {
        val result = service.issue(body)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @GetMapping
    fun retrieveAll(
        @RequestParam(defaultValue = "0") offset: Long,
        @RequestParam(defaultValue = "20") limit: Int,
        @RequestParam paid: Boolean?,
        @RequestParam dateAfter: String?,
        @RequestParam dateBefore: String?
    ): ResponseEntity<List<Invoice>> {
        val result = service.retrieveAll(offset, limit, paid, dateAfter, dateBefore)

        return ResponseEntity
            .ok(result)
    }

    @GetMapping("/{id}")
    fun retrieveById(
        @PathVariable id: String
    ): ResponseEntity<Invoice> {
        val result = service.retrieveById(id)

        return ResponseEntity
            .ok(result)
    }

    @PostMapping("/{id}/mark-as-paid")
    fun markAsPaid(
        @PathVariable id: String
    ): ResponseEntity<Invoice> {
        val result = service.markAsPaid(id)

        return ResponseEntity
            .ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: String
    ): ResponseEntity<Void> {
        service.deleteById(id)

        return ResponseEntity
            .noContent()
            .build()
    }
}