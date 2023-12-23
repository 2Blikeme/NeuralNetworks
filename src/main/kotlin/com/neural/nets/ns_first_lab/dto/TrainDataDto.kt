package com.neural.nets.ns_first_lab.dto

data class TrainDataDto(
    var dataVectors: MutableList<IntArray>,
    var answersDataVector: MutableList<Int>
)
