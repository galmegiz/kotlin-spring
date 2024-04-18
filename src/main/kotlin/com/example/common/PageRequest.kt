package com.example.common

class PageRequest(
    val pageNumber: Int,
    val pageSize: Int,
) {
    companion object{
        fun of(
            pageNumber: Int = 0,
            pageSize: Int = 100
        ): PageRequest{
            return PageRequest(
                pageNumber,
                pageSize
            )
        }
    }
}