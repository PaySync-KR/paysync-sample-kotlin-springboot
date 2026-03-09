package kr.paysync.sample.backend.common.dto

class PaginatedApiResponse<T>(
    val code: String,
    val data: List<T>,
    val totalItems: Long
)