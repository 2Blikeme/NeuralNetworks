package com.neural.nets.ns_first_lab.neuron

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.enums.HebbMetadataKeys
import com.neural.nets.ns_first_lab.service.MatrixService
import com.neural.nets.ns_first_lab.service.NeuronNetworkService
import com.neural.nets.ns_first_lab.service.delta_rule.DeltaRuleService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.File

@SpringBootTest
@ExtendWith(SpringExtension::class)
class DeltaRuleServiceTest {

    @Autowired
    private lateinit var deltaRuleService: DeltaRuleService

    @Autowired
    private lateinit var neuronNetworkService: NeuronNetworkService

    @Autowired
    private lateinit var matrixService: MatrixService

//    @Test
//    fun trainTest() {
//        val v1 = mutableListOf(1, 1, 1).toIntArray()
//        val v2 = mutableListOf(1, 1, 0).toIntArray()
//        val v3 = mutableListOf(1, 0, 1).toIntArray()
//        val v4 = mutableListOf(1, 0, 0).toIntArray()
//
//        val answ = mutableListOf(mutableListOf(1, 1, -1, -1, -1))
//
//        val data = mutableListOf(v1, v2, v3, v4)
//
//        val trainDataDto = TrainDataDto(data, mutableListOf(1, 1, -1, -1, -1))
//
//        val metadata = mapOf("eps" to 0.01, "alpha" to 0.1)
//
//        val train = deltaRuleService.train(trainDataDto, answ, metadata)
//
//        println(train)
//
//    }

    @Test
    fun trainTest2() {

        val objectMapper = jacksonObjectMapper()

        val file = File("src/test/resources/matrix_dto_list_mnogo.json")

        val matrixDtos : ArrayList<MatrixDto> = objectMapper.readValue(file.readText())

        for (i in 0 until matrixDtos.size) {
            matrixService.saveMatrix(matrixDtos[i])
        }

        val metadata = mutableMapOf<String, Any>()
        metadata["eps"] = 0.01
        metadata["alpha"] = 0.1

        val train = neuronNetworkService.trainWithDeltaRule(metadata)

        println(train)

        matrixService.deleteMatrixByIds(listOf("A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"))

    }



}