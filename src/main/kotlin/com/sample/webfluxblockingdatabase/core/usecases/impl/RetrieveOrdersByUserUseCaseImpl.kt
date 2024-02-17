package com.sample.webfluxblockingdatabase.core.usecases.impl

import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import com.sample.webfluxblockingdatabase.core.gateway.PurchaseOrderDataSourceGateway
import com.sample.webfluxblockingdatabase.core.usecases.RetrieveOrdersByUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

@Service
class RetrieveOrdersByUserUseCaseImpl(
    private val orderDataSourceGateway: PurchaseOrderDataSourceGateway
) : RetrieveOrdersByUserUseCase {

    override fun execute(userId: Int): Flux<PurchaseOrderResponseDto> {
        return Flux.fromStream {
            orderDataSourceGateway
                .findByUserId(userId)
                .stream()
                .map { purchaseOrder ->
                    PurchaseOrderResponseDto(
                        orderId = purchaseOrder.id!!,
                        userId = purchaseOrder.userId,
                        productId = purchaseOrder.productId,
                        amount = purchaseOrder.amount,
                        status = purchaseOrder.status
                    )
                }
        }.subscribeOn(Schedulers.boundedElastic())
    }
}