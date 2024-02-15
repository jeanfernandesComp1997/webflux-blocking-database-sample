package com.sample.webfluxblockingdatabase.application.controller

import com.sample.webfluxblockingdatabase.application.api.order.PurchaseOrderApi
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import com.sample.webfluxblockingdatabase.core.usecases.ProcessOrderFulfillmentUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientRequestException
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@RestController
class PurchaseOrderController(
    private val orderFulfillmentUseCase: ProcessOrderFulfillmentUseCase
) : PurchaseOrderApi {

    override fun order(requestDtoMono: Mono<PurchaseOrderRequestDto>): Mono<ResponseEntity<PurchaseOrderResponseDto>> {
        return this.orderFulfillmentUseCase.execute(requestDtoMono)
            .map { purchaseOrderResponse ->
                ResponseEntity
                    .created(URI("/orders/${purchaseOrderResponse.orderId}"))
                    .body(purchaseOrderResponse)
            }
            .onErrorReturn(WebClientResponseException::class.java, ResponseEntity.badRequest().build())
            .onErrorReturn(
                WebClientRequestException::class.java,
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()
            )
    }

    override fun getOrdersByUserId(userId: Int): Flux<PurchaseOrderResponseDto> {
        TODO("Not yet implemented")
    }
}