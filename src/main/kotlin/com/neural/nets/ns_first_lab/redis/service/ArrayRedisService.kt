package com.neural.nets.ns_first_lab.redis.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class ArrayRedisService(
    val intArrayRedisService: RedisTemplate<String, List<Int>>
) {


    fun saveIntArray(id: String, arr: List<Int>) {
        intArrayRedisService.opsForValue().set(id, arr)
    }

    fun findIntArrayById(id: String): List<Int>? {
        return intArrayRedisService.opsForValue().get(id)
    }


}