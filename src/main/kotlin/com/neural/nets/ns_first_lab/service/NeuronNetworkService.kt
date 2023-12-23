package com.neural.nets.ns_first_lab.service

import com.neural.nets.ns_first_lab.dto.ResponseResultDto
import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.enums.HebbMetadataKeys
import com.neural.nets.ns_first_lab.enums.MatrixIndexes
import com.neural.nets.ns_first_lab.redis.service.ArrayRedisService
import com.neural.nets.ns_first_lab.redis.service.MatrixRedisService
import com.neural.nets.ns_first_lab.redis.service.ObjectRedisService
import com.neural.nets.ns_first_lab.service.delta_rule.DeltaRuleService
import com.neural.nets.ns_first_lab.service.hebb.HebbService
import org.springframework.stereotype.Service

@Service
class NeuronNetworkService(
    val hebbService: HebbService,
    val matrixRedisService: MatrixRedisService,
    val arrayRedisService: ArrayRedisService,
    val objectRedisService: ObjectRedisService,
    val deltaRuleService: DeltaRuleService
) {

    fun trainWithHebbRule(metadata: MutableMap<String, Any>): Unit {

        val ids = listOf(
            MatrixIndexes.A1.name,
            MatrixIndexes.A2.name,
            MatrixIndexes.B1.name,
            MatrixIndexes.B2.name
        )

        val savedMatrices = matrixRedisService.findByIds(ids).filter {
            it.key != MatrixIndexes.C.name
        }.toSortedMap().values.toList()


        val answ = when (metadata[HebbMetadataKeys.activationMode.name]) {
            HebbMetadataKeys.BINARY.name -> mutableListOf(1, 1, 0, 0)
            HebbMetadataKeys.BIPOLAR.name -> mutableListOf(1, 1, -1, -1)
            else -> throw Exception()
        }

        val trainData = formToTrainData(savedMatrices, answ)
        metadata["y_tr"] = answ
        val weigths = hebbService.train(trainData, metadata)
        arrayRedisService.saveIntArray(HebbMetadataKeys.weigths.name, weigths)
        objectRedisService.saveObject(
            HebbMetadataKeys.presentation.name,
            metadata[HebbMetadataKeys.activationMode.name].toString()
        )
    }

    fun predictByHebb(): ResponseResultDto {
        val savedMatrix = matrixRedisService.findMatrixById(MatrixIndexes.C.name) ?: throw Exception()

        val presentation = objectRedisService.findObjectById(HebbMetadataKeys.presentation.name) ?: throw Exception()
        val formedData = formToTrainData(listOf(savedMatrix), List(1) { 0 })
        val weights = arrayRedisService.findIntArrayById(HebbMetadataKeys.weigths.name) ?: throw Exception()

        val predictedClass = hebbService.predict(formedData, weights, HebbMetadataKeys.valueOf(presentation.toString()))

        return ResponseResultDto(mutableMapOf("predictedClass" to predictedClass))

    }

    fun trainWithDeltaRule(metadata: MutableMap<String, Any>): MutableList<MutableList<Double>> {
        val ids = listOf(
            MatrixIndexes.A1.name,
            MatrixIndexes.A2.name,
            MatrixIndexes.B1.name,
            MatrixIndexes.B2.name,
            MatrixIndexes.C1.name,
            MatrixIndexes.C2.name,
            MatrixIndexes.D1.name,
            MatrixIndexes.D2.name,
            MatrixIndexes.E1.name,
            MatrixIndexes.E2.name,
        )
        val savedMatrices = matrixRedisService.findByIds(ids).filter {
            it.key != MatrixIndexes.C.name
        }.toSortedMap().values.toList()


        val dataVectors = mutableListOf<MutableList<Int>>()
        for (savedMatrix in savedMatrices) {
            if (savedMatrix == null) {
                throw Exception()
            }
            val matrixVector = mutableListOf(1)
            savedMatrix.matrix.map { row ->
                row.map { el ->
                    matrixVector.add(el)
                }
            }
            dataVectors.add(matrixVector)
        }

        val answ = mutableListOf(
            mutableListOf(1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1),
            mutableListOf(1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1),
            mutableListOf(1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1),
            mutableListOf(1, -1, -1, -1, -1, -1, -1, 1, 1, -1, -1),
            mutableListOf(1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1),

        )

        return deltaRuleService.train(dataVectors, answ, metadata)

    }

    fun predictByDeltaRule(): Int {
        val savedMatrix = matrixRedisService.findMatrixById(MatrixIndexes.C.name) ?: throw Exception()
        val weights = arrayRedisService.findIntArrayById(HebbMetadataKeys.weigths.name) ?: throw Exception()


        val matrixVector = mutableListOf(1)
        savedMatrix.matrix.map { row ->
            row.map { el ->
                matrixVector.add(el)
            }
        }

        deltaRuleService.predict(matrixVector, weights)





//
//        val formedData = formToTrainData(listOf(savedMatrix), List(1) { 0 })
//        val weights = arrayRedisService.findIntArrayById(HebbMetadataKeys.weigths.name) ?: throw Exception()
//
//        val predictedClass = hebbService.predict(formedData, weights, HebbMetadataKeys.valueOf(presentation.toString()))
//
//        return ResponseResultDto(mutableMapOf("predictedClass" to predictedClass))


    }

    private fun formToTrainData(data: List<Matrix>, answ: List<Int>): TrainDataDto {
        val trainData = TrainDataDto(mutableListOf(), mutableListOf())

        var i = 0
        data.map { matrix ->
            val matrixVector = mutableListOf<Int>()
            matrix.matrix.map { row ->
                row.map {
                    matrixVector.add(it)
                }
            }
            trainData.dataVectors.add(matrixVector.toIntArray())
            trainData.answersDataVector.add(answ[i])
            i++
        }

        return trainData
    }
}