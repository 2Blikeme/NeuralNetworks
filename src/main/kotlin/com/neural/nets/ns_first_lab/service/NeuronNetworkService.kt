package com.neural.nets.ns_first_lab.service

import com.neural.nets.ns_first_lab.dto.ResponseResultDto
import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.entity.Matrix
import com.neural.nets.ns_first_lab.enums.HebbMetadataKeys
import com.neural.nets.ns_first_lab.enums.MatrixIndexes
import com.neural.nets.ns_first_lab.redis.service.ArrayRedisService
import com.neural.nets.ns_first_lab.redis.service.MatrixRedisService
import com.neural.nets.ns_first_lab.redis.service.ObjectRedisService
import com.neural.nets.ns_first_lab.service.hebb.HebbService
import org.springframework.stereotype.Service

@Service
class NeuronNetworkService(
    val hebbService: HebbService,
    val matrixRedisService: MatrixRedisService,
    val arrayRedisService: ArrayRedisService,
    val objectRedisService: ObjectRedisService
) {

    fun trainWithHebbRule(metadata: MutableMap<String, Any>): Unit {

        val ids = MatrixIndexes.values().filter {
            it.name != MatrixIndexes.C.name
        }.map {
            it.name
        }.toList()

        val savedMatrices = matrixRedisService.findByIds(ids).filter {
            it.key != MatrixIndexes.C.name
        }.toSortedMap().values.toList()

//        val answ = mutableListOf<Int>()
//        for (i in savedMatrices.indices) {
//            answ.add(when (metadata[HebbMetadataKeys.activationMode.name]) {
//                HebbMetadataKeys.BIPOLAR.name -> if (i % 2 == 0) 1 else -1
//                HebbMetadataKeys.BINARY.name -> if (i % 2 == 0) 1 else 0
//                else -> throw Exception()
//            })
//        }


        val answ = when (metadata[HebbMetadataKeys.activationMode.name]) {
            HebbMetadataKeys.BINARY.name -> mutableListOf(1, 1, 0, 0)
            HebbMetadataKeys.BIPOLAR.name -> mutableListOf(1, 1, -1, -1)
            else -> throw Exception()
        }

        val trainData = formToTrainData(savedMatrices, answ)
        metadata["y_tr"] = answ
        val weigths = hebbService.train(trainData, metadata)
        arrayRedisService.saveIntArray(HebbMetadataKeys.weigths.name, weigths)
        objectRedisService.saveObject(HebbMetadataKeys.presentation.name,
            metadata[HebbMetadataKeys.activationMode.name].toString())
    }

    fun predictByHebb() : ResponseResultDto {
        val savedMatrix = matrixRedisService.findMatrixById(MatrixIndexes.C.name) ?: throw Exception()

        val presentation = objectRedisService.findObjectById(HebbMetadataKeys.presentation.name) ?: throw Exception()
        val formedData = formToTrainData(listOf(savedMatrix), List(1) {0})
        val weights = arrayRedisService.findIntArrayById(HebbMetadataKeys.weigths.name) ?: throw Exception()

        val predictedClass = hebbService.predict(formedData, weights, HebbMetadataKeys.valueOf(presentation.toString()))

        return ResponseResultDto(mutableMapOf("predictedClass" to predictedClass))

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