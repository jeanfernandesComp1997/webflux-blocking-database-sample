package com.sample.webfluxblockingdatabase.core.gateway

import com.sample.webfluxblockingdatabase.core.domain.dto.TransactionRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.TransactionResponseDto
import com.sample.webfluxblockingdatabase.core.domain.dto.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserGateway {

    fun authorizeTransaction(requestDto: TransactionRequestDto): Mono<TransactionResponseDto>
    fun retrieveAllUsers(): Flux<UserDto>
}