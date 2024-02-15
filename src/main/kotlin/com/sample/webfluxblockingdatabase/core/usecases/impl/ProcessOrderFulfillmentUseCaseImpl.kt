package com.sample.webfluxblockingdatabase.core.usecases.impl

import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderRequestDto
import com.sample.webfluxblockingdatabase.core.domain.dto.PurchaseOrderResponseDto
import com.sample.webfluxblockingdatabase.core.domain.dto.RequestContext
import com.sample.webfluxblockingdatabase.core.domain.dto.TransactionRequestDto
import com.sample.webfluxblockingdatabase.core.domain.entity.PurchaseOrder
import com.sample.webfluxblockingdatabase.core.domain.enums.OrderStatus
import com.sample.webfluxblockingdatabase.core.domain.enums.TransactionStatus
import com.sample.webfluxblockingdatabase.core.gateway.ProductGateway
import com.sample.webfluxblockingdatabase.core.gateway.PurchaseOrderDataSourceGateway
import com.sample.webfluxblockingdatabase.core.gateway.UserGateway
import com.sample.webfluxblockingdatabase.core.usecases.ProcessOrderFulfillmentUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class ProcessOrderFulfillmentUseCaseImpl(
    private val orderDataSourceGateway: PurchaseOrderDataSourceGateway,
    private val productGateway: ProductGateway,
    private val userGateway: UserGateway
) : ProcessOrderFulfillmentUseCase {

    override fun execute(requestDtoMono: Mono<PurchaseOrderRequestDto>): Mono<PurchaseOrderResponseDto> {
        return requestDtoMono
            .map { purchaseOrderRequest ->
                RequestContext(purchaseOrderRequest)
            }
            .flatMap { requestContext ->
                productRequestResponse(requestContext)
            }
            .doOnNext { requestContext ->
                requestContext.transactionRequestDto = TransactionRequestDto(
                    userId = requestContext.purchaseOrderRequestDto.userId,
                    amount = requestContext.productDto!!.price
                )
            }
            .flatMap { requestContext ->
                userRequestResponse(requestContext)
            }
            .map { requestContext ->
                val status = requestContext.transactionResponseDto!!.status
                val orderStatus =
                    if (TransactionStatus.APPROVED == status) OrderStatus.COMPLETED else OrderStatus.FAILED
                PurchaseOrder(
                    productId = requestContext.productDto!!.id,
                    userId = requestContext.purchaseOrderRequestDto.userId,
                    amount = requestContext.productDto!!.price,
                    status = orderStatus
                )
            }
            .map { purchaseOrder ->
                orderDataSourceGateway.save(purchaseOrder)
            }
            .map { purchaseOrder ->
                PurchaseOrderResponseDto(
                    orderId = purchaseOrder.id!!,
                    userId = purchaseOrder.userId,
                    productId = purchaseOrder.productId,
                    amount = purchaseOrder.amount,
                    status = purchaseOrder.status
                )
            }
            .subscribeOn(Schedulers.boundedElastic())
    }

    private fun productRequestResponse(rc: RequestContext): Mono<RequestContext> {
        return productGateway
            .getProductById(rc.purchaseOrderRequestDto.productId)
            .doOnNext { product ->
                rc.productDto = product
            }
            .thenReturn(rc)
    }

    private fun userRequestResponse(rc: RequestContext): Mono<RequestContext> {
        return userGateway
            .authorizeTransaction(rc.transactionRequestDto!!)
            .doOnNext { transactionResponse ->
                rc.transactionResponseDto = transactionResponse
            }
            .thenReturn(rc)
    }
}