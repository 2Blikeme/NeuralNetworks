package com.neural.nets.ns_first_lab.service.delta_rule

import com.neural.nets.ns_first_lab.dto.TrainDataDto
import com.neural.nets.ns_first_lab.service.NeuronNetwork
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.abs

@Service
class DeltaRuleService : NeuronNetwork {
    fun train(
        data: MutableList<MutableList<Int>>,
        answersDataVector: MutableList<MutableList<Int>>,
        metaData: Map<String, Any>
    ): MutableList<MutableList<Double>> {

        val eps = metaData["eps"] as Double
        val alpha = metaData["alpha"] as Double

        // в weigths[0] лежит w0
        // в data.dataVectors в каждом векторе[0] лежит x0
        // в data.answ в каждом векторе[0] лежит 1
        val weights = MutableList(data.size) {
            MutableList(data[0].size) {
                Random(1488).nextDouble(-0.5, 0.5)
            }
        }

        var error = 0.0
        var tries = 0
        do {

            tries++
            println(tries)
            error = 0.0

            val oldWeights = weights.map { it.map { row -> row }.toList() }.toList()
            for ((i, vector) in data.withIndex()) {
                var uIn = 0.0
                for (weightRowIndex in 0 until weights.size) {
                    for (j in vector.indices) {
                        uIn += weights[weightRowIndex][j] * vector[j]
                    }
                    for (answIndex in 0 until answersDataVector.size) {
                        for (j in weights[weightRowIndex].indices) {
                            weights[weightRowIndex][j] += alpha * (answersDataVector[answIndex][i + 1] - uIn) * vector[j]
                        }
                    }
                }
            }

            for (i in 0 until weights.size) {
                for (j in 0 until weights[0].size) {
                    error += abs(oldWeights[i][j] - weights[i][j])
                }
            }
        } while (error >= eps)

        return weights
    }

    fun predict(data: MutableList<Int>, weights: List<Int>) : Int {



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