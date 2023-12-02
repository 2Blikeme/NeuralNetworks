package com.neural.nets.ns_first_lab.service

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.redis.service.RedisService
import org.springframework.stereotype.Service

@Service
class MatrixService(val redisService: RedisService) {

    /**
     * Сохраняет матрицу
     */
    fun saveMatrix(matrixDto: MatrixDto) {
        val matrix = Matrix(matrix = matrixDto.matrix)
        redisService.saveMatrix(matrixDto.id, matrix)
    }

    fun findMatrixById(id: String): Matrix? {
        return redisService.findMatrixById(id)
    }

}
