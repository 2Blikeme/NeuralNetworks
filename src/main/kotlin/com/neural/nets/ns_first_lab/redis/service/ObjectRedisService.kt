package com.neural.nets.ns_first_lab.redis.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class ObjectRedisService(
    val objectRedisTemplate: RedisTemplate<String, Any>
) {

    fun saveObject(id: String, obj: Any) {
        objectRedisTemplate.opsForValue().set(id, obj)
    }

    fun findObjectById(id: String): Any? {
        return objectRedisTemplate.opsForValue().get(id)
    }


}