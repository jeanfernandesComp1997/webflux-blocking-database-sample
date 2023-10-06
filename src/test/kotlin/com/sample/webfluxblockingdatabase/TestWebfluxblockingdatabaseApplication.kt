package com.sample.webfluxblockingdatabase

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestWebfluxblockingdatabaseApplication

fun main(args: Array<String>) {
	fromApplication<WebfluxblockingdatabaseApplication>().with(TestWebfluxblockingdatabaseApplication::class).run(*args)
}
