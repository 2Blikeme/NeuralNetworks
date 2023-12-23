package com.neural.nets.ns_first_lab.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.enums.MatrixIndexes
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.File

@SpringBootTest
@ExtendWith(SpringExtension::class)
class ObjectsTest {

    @Test
    fun matrixDtoToMatrix() {

        val m = Array(6) { IntArray(5)}

        m[0] = intArrayOf(1, 0, 0, 0, 1)
        m[1] = intArrayOf(1, 1, 1, 0, 0)
        m[2] = intArrayOf(1, 1, 0, 1, 1)
        m[3] = intArrayOf(0, 0, 0, 0, 0)
        m[4] = intArrayOf(0, 0, 0, 0, 0)
        m[5] = intArrayOf(0, 0, 0, 0, 1)

        val objectMapper = jacksonObjectMapper()

        val file = File("src/test/resources/matrix_dto.json")

        val matrixDto : MatrixDto = objectMapper.readValue(file.readText())

        assert(matrixDto.id == MatrixIndexes.A1.name)
        assert(matrixDto.matrixInfo.matrix.contentDeepEquals(m))
    }
}