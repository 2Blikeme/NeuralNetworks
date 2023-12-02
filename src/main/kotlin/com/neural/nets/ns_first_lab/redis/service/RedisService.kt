package com.neural.nets.ns_first_lab.redis.service

import com.neural.nets.ns_first_lab.entity.Matrix
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(val redisTemplate: RedisTemplate<String, Matrix>) {

    fun saveMatrix(id: String, matrix: Matrix) {
        redisTemplate.opsForValue().set(id, matrix)
    }

    fun findMatrixById(id: String) : Matrix? {
        return redisTemplate.opsForValue().get(id)
    }

}