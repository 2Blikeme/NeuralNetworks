package com.neural.nets.ns_first_lab.controller

import com.neural.nets.ns_first_lab.dto.ResponseResultDto
import com.neural.nets.ns_first_lab.service.NeuronNetworkService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nn")
class NeuralNetworkController(
    val neuronNetworkService: NeuronNetworkService,
) {

    @PostMapping("/hebb", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun trainByHebb(@RequestBody metadata: MutableMap<String, Any>) {
        return neuronNetworkService.trainWithHebbRule(metadata)
    }

    @GetMapping("/hebb/predict", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun predictByHebb() :ResponseEntity<ResponseResultDto> {
        return ResponseEntity.ok(neuronNetworkService.predictByHebb())
    }
}
