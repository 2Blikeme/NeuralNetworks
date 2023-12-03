package com.neural.nets.ns_first_lab.redis.service

import com.neural.nets.ns_first_lab.entity.Matrix
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(val redisTemplate: RedisTemplate<String, Matrix>) {

    @Value("\${redis.matrix.map.name}")
    lateinit var matrixMapName : String

    fun saveMatrix(id: String, matrix: Matrix) {
        redisTemplate.opsForHash<String, Matrix>().put(matrixMapName, id, matrix)
    }

    fun findMatrixById(id: String) : Matrix? {
        return redisTemplate.opsForHash<String, Matrix>().get(matrixMapName, id)
    }

    fun findByIds(ids: List<String>): MutableMap<String, Matrix> {
        return redisTemplate.opsForHash<String, Matrix>().entries(matrixMapName)
    }

    fun deleteByIds(ids: List<String>) {
        for (id in ids) {
            redisTemplate.opsForHash<String, Matrix>().delete(matrixMapName, id)
        }
    }
}