package com.sample.webfluxblockingdatabase.core.domain.dto

import com.sample.webfluxblockingdatabase.core.domain.enums.OrderStatus

data class PurchaseOrderResponseDto(
    val orderId: Int,
    val userId: Int,
    val productId: String,
    val amount: Double,
    val status: OrderStatus
)
