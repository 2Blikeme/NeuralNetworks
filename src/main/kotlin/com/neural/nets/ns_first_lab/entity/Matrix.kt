package com.neural.nets.ns_first_lab.entity

import org.springframework.data.redis.core.RedisHash

@RedisHash("matrix")
data class Matrix(
    var matrix: Array<IntArray> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        return matrix.contentDeepEquals(other.matrix)
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }
}
