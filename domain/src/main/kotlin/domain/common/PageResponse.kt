package domain.common

data class PageResponse<T>(
    val data: List<T>,
    val currentPage: Int,
    val lastPage: Int,
    val pageSize: Int,
    val next: Boolean
)