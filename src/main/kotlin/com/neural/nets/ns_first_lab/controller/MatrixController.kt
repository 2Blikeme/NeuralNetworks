package com.neural.nets.ns_first_lab.controller

import com.neural.nets.ns_first_lab.dto.MatrixDto
import com.neural.nets.ns_first_lab.dto.MatrixRequestDto
import com.neural.nets.ns_first_lab.service.MatrixService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/matrix")
class MatrixController(val matrixService: MatrixService) {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findMatrixById(@PathVariable id: String): ResponseEntity<MatrixDto> {
        return ResponseEntity.ok(matrixService.findMatrixById(id))
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createMatrix(@RequestBody matrixDto: MatrixDto) : ResponseEntity<MatrixDto> {
        val savedMatrix = matrixService.saveMatrix(matrixDto)
        return ResponseEntity.ok(savedMatrix)
    }

    @DeleteMapping
    fun deleteMatrix(@RequestBody matrixRequestDto: MatrixRequestDto): ResponseEntity<String> {
        matrixService.deleteMatrixByIds(matrixRequestDto.ids)
        return ResponseEntity.ok().build()
    }
}