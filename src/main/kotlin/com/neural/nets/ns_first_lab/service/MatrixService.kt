package com.neural.nets.ns_first_lab.service

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.redis.service.MatrixRedisService
import org.springframework.stereotype.Service

@Service
class MatrixService(val matrixRedisService: MatrixRedisService) {

    /**
     * Сохраняет матрицу
     */
    fun saveMatrix(matrixDto: MatrixDto): MatrixDto {
        matrixRedisService.saveMatrix(matrixDto.id, matrixDto.matrixInfo)
        return matrixDto
    }

    fun findMatrixById(id: String): MatrixDto? {
        val matrix = matrixRedisService.findMatrixById(id)?: Matrix()
        return MatrixDto(id, matrix)
    }

    fun findByIds(ids: List<String>): List<MatrixDto> {
        val matrixMap = matrixRedisService.findByIds(ids)

        val matrixDtoList: MutableList<MatrixDto> = mutableListOf()
        matrixMap.entries.forEach {
            matrixDtoList.add(MatrixDto(it.key, it.value))
        }
        return matrixDtoList
    }

    fun deleteMatrixByIds(ids: List<String>) {
        matrixRedisService.deleteByIds(ids)
    }
}
