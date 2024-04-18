package com.example.common

import kotlin.math.ceil

class Page<T>(
    val content : List<T>,
    val pageRequest: PageRequest,
    totalElements: Int,
) {
    val size: Int = content.size
    val pageNumber: Int = pageRequest.pageNumber
    val isEmpty: Boolean
        get() = content.isEmpty()
    val isStart: Boolean
        get() = pageRequest.pageNumber == 0
    val isEnd: Boolean
        get() = pageRequest.pageNumber == totalPageCount
    val totalPageCount: Int = ceil(totalElements / pageRequest.pageSize.toDouble()).toInt()
}