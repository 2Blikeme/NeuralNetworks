package com.neural.nets.ns_first_lab.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class MainController



@PostMapping("/data")
fun getUserData(): String {
    return ""
}


