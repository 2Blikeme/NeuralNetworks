package com.neural.nets.ns_first_lab.neuron

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.enums.HebbMetadataKeys
import com.neural.nets.ns_first_lab.enums.MatrixIndexes
import com.neural.nets.ns_first_lab.service.MatrixService
import com.neural.nets.ns_first_lab.service.NeuronNetworkService
import com.neural.nets.ns_first_lab.service.hebb.ActivationFunc
import com.neural.nets.ns_first_lab.service.hebb.HebbService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.File
import java.util.Collections

@SpringBootTest
@ExtendWith(SpringExtension::class)
class HebbServiceTest {

    @Autowired
    private lateinit var hebbService: HebbService

    @Autowired
    private lateinit var neuronNetworkService: NeuronNetworkService

    @Autowired
    private lateinit var matrixService: MatrixService

//    @Test
//    fun prepareTrainDataTest() {
//
//        val m = Array(3) { IntArray(3)}
//
//        m[0] = intArrayOf(1, 0, 0)
//        m[1] = intArrayOf(1, 1, 1)
//        m[2] = intArrayOf(1, 1, 0)
//
//        val matrix = Matrix(m)
//
////        val prepareTrainData = hebbService.prepareTrainData(listOf(matrix) , ActivationFunc::binary)
//
//        val expectedValue = intArrayOf(1, 1, 0, 0, 1, 1, 1, 1, 1, 0)
//
//        assert(expectedValue.contentEquals(prepareTrainData[0]))
//
//    }

    @Test
    fun trainTest() {

        val firstMatrix =
            mutableListOf(
                1, 1, 0, 1, 1,
                1, 0, 1, 0, 1,
                1, 0, 1, 0, 1,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1).toIntArray()
        val secondMatrix =
            mutableListOf(
                1, 1, 0, 1, 1,
                1, 0, 1, 0, 1,
                1, 0, 0, 1, 1,
                1, 0, 0, 0, 1,
                1, 0, 0, 0, 1).toIntArray()
        val thirdMatrix =
            mutableListOf(
                0, 1, 1, 1, 0,
                0, 1, 0, 1, 0,
                0, 1, 0, 1, 0,
                1, 1, 1, 1, 1,
                1, 0, 0, 0, 1).toIntArray()
        val forthMatrix =
            mutableListOf(
                0, 1, 0, 1, 0,
                0, 1, 1, 1, 0,
                0, 1, 1, 1, 0,
                1, 1, 0, 1, 1,
                1, 0, 1, 0, 1).toIntArray()

        val trainVectors = mutableListOf(firstMatrix, secondMatrix, thirdMatrix, forthMatrix)
//        val answers = mutableListOf(1, -1, 1, -1)
        val answers = mutableListOf(1, 0, 1, 0)

        val trainData = TrainDataDto(trainVectors, answers)

        val map = mutableMapOf<String, Any>()
        map["y_tr"] = answers
//        map["activationMode"] = HebbMetadataKeys.BIPOLAR.name
        map["activationMode"] = HebbMetadataKeys.BINARY.name

        val train = hebbService.train(trainData, map)

        println(train)
    }

    @Test
    fun trainTest2() {

        val firstMatrix =
            mutableListOf(
                1, 0, 1,
                1, 1, 1,
                0, 0, 1).toIntArray()
        val secondMatrix =
            mutableListOf(
                1, 1, 1,
                1, 0, 1,
                1, 0, 1).toIntArray()

        val trainVectors = mutableListOf(firstMatrix, secondMatrix)
//        val answers = mutableListOf(1, -1)
        val answers = mutableListOf(1, 0)

        val trainData = TrainDataDto(trainVectors, answers)

        val map = mutableMapOf<String, Any>()
        map.put("y_tr", answers)
//        map["activationMode"] = HebbMetadataKeys.BIPOLAR.name
        map["activationMode"] = HebbMetadataKeys.BINARY.name

        val train = hebbService.train(trainData, map)

        println(train)
    }

    @Test
    fun trainTestFullCycle() {


        val objectMapper = jacksonObjectMapper()

        val file = File("src/test/resources/matrix_dto_list.json")

        val matrixDtos : ArrayList<MatrixDto> = objectMapper.readValue(file.readText())

        matrixService.saveMatrix(matrixDtos[0])
        matrixService.saveMatrix(matrixDtos[1])
        matrixService.saveMatrix(matrixDtos[2])
        matrixService.saveMatrix(matrixDtos[3])

        val metadata = mutableMapOf<String, Any>()
        metadata[HebbMetadataKeys.activationMode.name] = HebbMetadataKeys.BINARY.name

        neuronNetworkService.trainWithHebbRule(metadata)

        matrixService.deleteMatrixByIds(listOf("A1", "A2", "B1", "B2"))
    }

    @Test
    fun predictTest() {

//        val toPredictVector = mutableListOf(1, 0, 1, 1, 1, 1, 0, 0, 1).toIntArray()
        val toPredictVector = mutableListOf(1, 1, 1, 1, 0, 1, 1, 0, 1).toIntArray()

        val trainDataDto = TrainDataDto(mutableListOf(toPredictVector), mutableListOf(0))

        val weights = listOf(0, 0, -2, 0, 0, 2, 0, 0, -2, 0)

        val predict = hebbService.predict(trainDataDto, weights, HebbMetadataKeys.BIPOLAR)

        assert(predict == 1)

    }
}