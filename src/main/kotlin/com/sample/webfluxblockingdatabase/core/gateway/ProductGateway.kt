package com.sample.webfluxblockingdatabase.core.gateway

import com.sample.webfluxblockingdatabase.core.domain.dto.ProductDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductGateway {

    fun getProductById(productId: String): Mono<ProductDto>
    fun retrieveAllProducts(): Flux<ProductDto>
}