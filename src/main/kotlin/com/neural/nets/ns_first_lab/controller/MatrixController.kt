package com.neural.nets.ns_first_lab.controller

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.service.MatrixService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/matrix")
class MatrixController(val matrixService: MatrixService) {

    @GetMapping
    fun findMatrixByIds(ids: List<String>): List<MatrixDto> {
        return matrixService.findByIds(ids);
    }

    @PostMapping
    fun createMatrix(matrixDto: MatrixDto) {


        matrixService.saveMatrix(matrixDto)

    }

}