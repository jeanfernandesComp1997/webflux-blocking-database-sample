package com.sample.webfluxblockingdatabase.gateway.webclient

import com.sample.webfluxblockingdatabase.core.domain.dto.TransactionRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.TransactionResponseDto
import com.sample.webfluxblockingdatabase.core.domain.dto.UserDto
import com.sample.webfluxblockingdatabase.core.gateway.UserGateway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserGatewayImpl(
    @Qualifier("userWebClient")
    private val client: WebClient
) : UserGateway {

    override fun authorizeTransaction(requestDto: TransactionRequestDto): Mono<TransactionResponseDto> {
        return this.client
            .post()
            .uri("transaction")
            .bodyValue(requestDto)
            .retrieve()
            .bodyToMono(TransactionResponseDto::class.java)
    }

    override fun retrieveAllUsers(): Flux<UserDto> {
        return this.client
            .get()
            .uri("all")
            .retrieve()
            .bodyToFlux(UserDto::class.java)
    }
}