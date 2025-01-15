package com.yunussemree.kotlin_spring_boot_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class KotlinSpringBootExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringBootExampleApplication>(*args)
}
