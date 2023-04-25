package com.learn.spr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LearnSprApplication

fun main(args: Array<String>) {
    runApplication<LearnSprApplication>(*args)
}
