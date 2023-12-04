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
    fun saveMatrix(matrixDto: MatrixDto): MatrixDto {
        redisService.saveMatrix(matrixDto.id, matrixDto.matrixInfo)
        return matrixDto
    }

    fun findMatrixById(id: String): MatrixDto? {
        val matrix = redisService.findMatrixById(id)?: Matrix()
        return MatrixDto(id, matrix)
    }

    fun findByIds(ids: List<String>): List<MatrixDto> {
        val matrixMap = redisService.findByIds(ids)

        val matrixDtoList: MutableList<MatrixDto> = mutableListOf()
        matrixMap.entries.forEach {
            matrixDtoList.add(MatrixDto(it.key, it.value))
        }
        return matrixDtoList
    }

    fun deleteMatrixByIds(ids: List<String>) {
        redisService.deleteByIds(ids)
    }
}
