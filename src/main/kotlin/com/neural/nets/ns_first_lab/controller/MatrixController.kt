package com.neural.nets.ns_first_lab.controller

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.dto.MatrixRequestDto
import com.neural.nets.ns_first_lab.service.MatrixService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/matrix")
class MatrixController(val matrixService: MatrixService) {

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findMatrixByIds(@RequestBody matrixRequestDto: MatrixRequestDto): ResponseEntity<List<MatrixDto>> {
        return ResponseEntity.ok(matrixService.findByIds(matrixRequestDto.ids))
    }

    @PostMapping
    fun createMatrix(@RequestBody matrixDto: MatrixDto) : ResponseEntity<String> {
        matrixService.saveMatrix(matrixDto)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun deleteMatrix(@RequestBody matrixRequestDto: MatrixRequestDto): ResponseEntity<String> {
        matrixService.deleteMatrixByIds(matrixRequestDto.ids)
        return ResponseEntity.ok().build()
    }
}