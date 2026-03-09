package kr.paysync.sample.backend.paysync.dto

class PaginatedApiResponse<T>(
    val code: String,
    val data: List<T>,
    val totalItems: Long
)