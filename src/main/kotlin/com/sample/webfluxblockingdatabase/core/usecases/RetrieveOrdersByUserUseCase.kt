package com.sample.webfluxblockingdatabase.core.usecases

import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import reactor.core.publisher.Flux

interface RetrieveOrdersByUserUseCase {

    fun execute(userId: Int): Flux<PurchaseOrderResponseDto>
}