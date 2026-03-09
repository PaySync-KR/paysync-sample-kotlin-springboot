package kr.paysync.sample.backend.paysync.dto

class ApiResponse<T>(
    val code: String,
    val data: T
)