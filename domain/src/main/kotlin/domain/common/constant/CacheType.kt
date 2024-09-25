package domain.common.constant

enum class CacheType(
    val cacheName: String,
    val capacity: Int,
    val maximumSize: Long,
    val expireTime: Long
) {
    DEFAULT(CacheConstant.DEFAULT, 100, 500L, 60L)
}