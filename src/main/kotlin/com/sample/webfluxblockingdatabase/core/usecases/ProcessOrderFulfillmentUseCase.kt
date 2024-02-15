package com.sample.webfluxblockingdatabase.core.usecases

import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import reactor.core.publisher.Mono

interface ProcessOrderFulfillmentUseCase {

    fun execute(requestDtoMono: Mono<PurchaseOrderRequestDto>): Mono<PurchaseOrderResponseDto>
}