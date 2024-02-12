package com.sample.webfluxblockingdatabase.core.domain.entity

import com.sample.webfluxblockingdatabase.core.domain.enums.OrderStatus

class PurchaseOrder(
    val id: Int,
    val productId: String,
    val userId: Int,
    val amount: Int,
    val status: OrderStatus
) {
}