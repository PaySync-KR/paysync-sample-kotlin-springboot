package kr.paysync.sample.backend.common

class ManyResult<T>(
    val items: List<T>,
    val totalItems: Long
) {

    operator fun component1() = items
    operator fun component2() = totalItems
}