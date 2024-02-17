package com.sample.webfluxblockingdatabase.application.controller

import com.sample.webfluxblockingdatabase.application.api.order.PurchaseOrderApi
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import com.sample.webfluxblockingdatabase.core.usecases.ProcessOrderFulfillmentUseCase
import com.sample.webfluxblockingdatabase.core.usecases.RetrieveOrdersByUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@RestController
class PurchaseOrderController(
    private val orderFulfillmentUseCase: ProcessOrderFulfillmentUseCase,
    private val retrieveOrdersByUserUseCase: RetrieveOrdersByUserUseCase
) : PurchaseOrderApi {

    override fun order(requestDtoMono: Mono<PurchaseOrderRequestDto>): Mono<ResponseEntity<PurchaseOrderResponseDto>> {
        return this.orderFulfillmentUseCase.execute(requestDtoMono)
            .map { purchaseOrderResponse ->
                ResponseEntity
                    .created(URI("/orders/${purchaseOrderResponse.orderId}"))
                    .body(purchaseOrderResponse)
            }
    }

    override fun getOrdersByUserId(userId: Int): Flux<PurchaseOrderResponseDto> {
        return retrieveOrdersByUserUseCase.execute(userId)
    }
}