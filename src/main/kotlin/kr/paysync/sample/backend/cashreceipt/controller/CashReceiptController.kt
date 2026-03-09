package kr.paysync.sample.backend.cashreceipt.controller

import kr.paysync.sample.backend.cashreceipt.dto.IssueCashReceiptRequest
import kr.paysync.sample.backend.cashreceipt.model.CashReceipt
import kr.paysync.sample.backend.cashreceipt.service.CashReceiptService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cash-receipts")
class CashReceiptController(
    private val service: CashReceiptService
) {

    @PostMapping
    fun issue(
        @RequestBody body: IssueCashReceiptRequest
    ): ResponseEntity<CashReceipt> {
        val result = service.issue(body)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @GetMapping("/{id}")
    fun retrieveById(
        @PathVariable id: String
    ): ResponseEntity<CashReceipt> {
        val result = service.retrieveById(id)

        return ResponseEntity.ok(result)
    }

    @PostMapping("/{id}/revoke")
    fun revokeById(
        @PathVariable id: String
    ): ResponseEntity<Unit> {
        val result = service.revokeById(id)

        return ResponseEntity.ok(result)
    }
}