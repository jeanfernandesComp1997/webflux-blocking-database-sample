package com.sample.webfluxblockingdatabase.gateway.webclient

import com.sample.webfluxblockingdatabase.core.domain.dto.ProductDto
import com.sample.webfluxblockingdatabase.core.gateway.ProductGateway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ProductGatewayImpl(
    @Qualifier("productWebClient")
    private val client: WebClient
) : ProductGateway {

    override fun getProductById(productId: String): Mono<ProductDto> {
        return client
            .get()
            .uri("{id}", productId)
            .retrieve()
            .bodyToMono(ProductDto::class.java)
    }

    override fun retrieveAllProducts(): Flux<ProductDto> {
        return client
            .get()
            .uri("all")
            .retrieve()
            .bodyToFlux(ProductDto::class.java)
    }
}