package com.mtek.poc.feed_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class FeedServiceApplication

fun main(args: Array<String>) {
    runApplication<FeedServiceApplication>(*args)
}
