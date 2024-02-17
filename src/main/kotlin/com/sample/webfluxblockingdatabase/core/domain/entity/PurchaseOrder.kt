package com.sample.webfluxblockingdatabase.core.domain.entity

import com.sample.webfluxblockingdatabase.core.domain.enums.OrderStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class PurchaseOrder(
    @Id
    @GeneratedValue
    var id: Int? = 0,
    val productId: String,
    val userId: Int,
    val amount: Double,
    val status: OrderStatus
)