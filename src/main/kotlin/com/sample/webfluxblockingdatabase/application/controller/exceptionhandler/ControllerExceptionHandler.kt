package com.sample.webfluxblockingdatabase.application.controller.exceptionhandler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import reactor.core.publisher.Mono

@ControllerAdvice
class ControllerExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(ControllerExceptionHandler::class.java)

    // this generic error handling is only for tests, for production never return details of the exception
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): Mono<ResponseEntity<Any>> {
        logger.info("Handling exception in thread: ${Thread.currentThread().name}")
        return Mono.just(
            ResponseEntity
                .internalServerError()
                .body(object {
                    val message = exception.message
                    val stackTrace = exception.stackTrace
                })
        )
    }
}