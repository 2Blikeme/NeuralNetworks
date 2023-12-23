package com.neural.nets.ns_first_lab.service.hebb

import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.enums.HebbMetadataKeys
import com.neural.nets.ns_first_lab.service.NeuronNetwork
import org.springframework.stereotype.Service

@Suppress("UNCHECKED_CAST")
@Service
class HebbService : NeuronNetwork {

    override fun train(data: TrainDataDto, metaData: Map<String, Any>): List<Int> {
        val activationMode = HebbMetadataKeys.valueOf(metaData[HebbMetadataKeys.activationMode.name].toString())

        val activatedVectors = prepareTrainData(data,
            if (activationMode == HebbMetadataKeys.BINARY) ActivationFunc::binary else ActivationFunc::bipolar)
        data.dataVectors = activatedVectors

        var tries = 0
        val yTr: MutableList<Int> = metaData["y_tr"] as MutableList<Int>
        val sFinished = MutableList(yTr.size) {0}
        val weights = MutableList(data.dataVectors.size) { MutableList(data.dataVectors[0].size) { 0 } }
        val resultWeights = MutableList(data.dataVectors[0].size) {0}

        do {
            sFinished.fill(0)
            resultWeights.fill(0)
            if (tries != 0) {

//                for (i in sFinished.indices) {
//                    if (sFinished[i] != yTr[i]) {
//                        yTr[i] = when (activationMode) {
//                            HebbMetadataKeys.BIPOLAR -> yTr[i] * -1
//                            HebbMetadataKeys.BINARY -> if (yTr[i] == 0) 1 else 0
//                            else -> continue
//                        }
//                    }
//                }
            }

            for (i in 0 until data.answersDataVector.size) {
                data.dataVectors[i].forEachIndexed { index, value ->
                    if (activationMode == HebbMetadataKeys.BIPOLAR) {
                        weights[i][index] += (value * yTr[i % yTr.size])
                    } else {
                        weights[i][index] +=
                            when (value) {
                                0 -> 0
                                1 -> if (yTr[i % yTr.size] == 1) 1 else -1
                                -1 -> if (yTr[i] == 0) -1 else throw Exception()
                                else -> throw Exception()
                            }
                    }
                }
            }
            for (i in 0 until weights[0].size) {
                for (vector in weights) {
                    resultWeights[i] += vector[i]
                }
            }

            data.dataVectors.forEachIndexed { i, vector ->
                vector.forEachIndexed { k, value ->
                    sFinished[i] += value * resultWeights[k]
                }
                sFinished[i] =
                    when (activationMode) {
                        HebbMetadataKeys.BIPOLAR -> if (sFinished[i] >= 0) 1 else -1
                        HebbMetadataKeys.BINARY -> if (sFinished[i] >= 0) 1 else 0
                        else -> throw Exception()
                    }
            }

            tries++
        } while (yTr != sFinished)
        return resultWeights
    }

    fun predict(data: TrainDataDto, weights: List<Int>, presentation: HebbMetadataKeys) : Int {
        val preparedVector = prepareTrainData(data,
            when (presentation) {
                HebbMetadataKeys.BINARY -> ActivationFunc::binary
                HebbMetadataKeys.BIPOLAR -> ActivationFunc::bipolar
                else -> throw Exception()
            }
        )[0]

        var finishedSignal = 0

        preparedVector.forEachIndexed { k, value ->
            finishedSignal += value * weights[k]
        }
        return if (finishedSignal >= 0) 1 else 2
    }


    fun prepareTrainData(data: TrainDataDto, activationFunc: (el: Int) -> Int): MutableList<IntArray> {
        val preparedData = mutableListOf<IntArray>()
        data.dataVectors.map { matrixVector ->
            val newVector = mutableListOf(1)
            matrixVector.map {
                newVector.add(activationFunc(it))
            }
            preparedData.add(newVector.toIntArray())
        }
        return preparedData
    }
}