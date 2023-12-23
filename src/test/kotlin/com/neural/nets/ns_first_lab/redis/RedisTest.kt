package com.neural.nets.ns_first_lab.redis

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.enums.MatrixIndexes
import com.neural.nets.ns_first_lab.redis.service.ArrayRedisService
import com.neural.nets.ns_first_lab.redis.service.MatrixRedisService
import com.neural.nets.ns_first_lab.redis.service.ObjectRedisService
import com.neural.nets.ns_first_lab.service.MatrixService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class RedisTest {

    @Autowired
    private lateinit var matrixService: MatrixService

    @Autowired
    private lateinit var matrixRedisService: MatrixRedisService

    @Autowired
    private lateinit var objectRedisService: ObjectRedisService

    @Autowired
    private lateinit var arrayRedisService: ArrayRedisService

    @Test
    fun saveMatrixTest() {
        val m = Array(5) { IntArray(5) { 0 } }
        val matrixDto = MatrixDto(MatrixIndexes.A1.name, Matrix(m))

        matrixService.saveMatrix(matrixDto)
        val savedMatrix = matrixService.findMatrixById(MatrixIndexes.A1.name)

        if (savedMatrix != null) {
            assert(savedMatrix.matrixInfo.matrix.contentDeepEquals(m))
        }
    }

    @Test
    fun saveIntArrayTest() {
        val m = listOf(1, 2, 3, 4, 5)
        arrayRedisService.saveIntArray("we", m)

        val saved = arrayRedisService.findIntArrayById("we")

        if (saved != null) {
            assert(m.equals(m))
        }
    }

    @Test
    fun saveAnyTest() {
        val m = "asdasd"
        objectRedisService.saveObject("test", m)
        val saved = objectRedisService.findObjectById("test")

        if (saved != null) {
            assert(m == m)
        }
    }


}