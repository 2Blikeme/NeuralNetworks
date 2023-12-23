package com.neural.nets.ns_first_lab.service

import com.neural.nets.ns_first_lab.dto.TrainDataDto

interface NeuronNetwork {

    fun train(data: TrainDataDto, metaData: Map<String, Any>) : List<Int>


}