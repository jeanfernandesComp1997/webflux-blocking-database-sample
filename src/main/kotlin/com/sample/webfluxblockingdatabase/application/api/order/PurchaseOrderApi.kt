package com.sample.webfluxblockingdatabase.application.api.order

import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("orders")
interface PurchaseOrderApi {

    @PostMapping
    fun order(@RequestBody requestDtoMono: Mono<PurchaseOrderRequestDto>): Mono<ResponseEntity<PurchaseOrderResponseDto>>

    @GetMapping("{id}")
    fun getOrdersByUserId(@PathVariable("id") userId: Int): Flux<PurchaseOrderResponseDto>
}