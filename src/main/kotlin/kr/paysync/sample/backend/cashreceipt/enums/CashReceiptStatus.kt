package kr.paysync.sample.backend.cashreceipt.enums

enum class CashReceiptStatus {

    PENDING, // 발행 대기
    ISSUED,  // 발행 완료
    FAILED,  // 발행 실패
    REVOKED; // 발행 취소
}