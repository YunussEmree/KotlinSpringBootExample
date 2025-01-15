package com.yunussemree.kotlin_spring_boot_example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeController {

    @GetMapping("/")
    fun index(): String = "\"Hello World!\""

}
