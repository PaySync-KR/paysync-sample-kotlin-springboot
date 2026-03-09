package kr.paysync.sample.backend.common.dto

class ApiResponse<T>(
    val code: String,
    val data: T
)