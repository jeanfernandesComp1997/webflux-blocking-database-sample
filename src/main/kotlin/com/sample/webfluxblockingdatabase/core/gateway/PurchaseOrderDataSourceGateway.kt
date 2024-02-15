package com.sample.webfluxblockingdatabase.core.gateway

import com.sample.webfluxblockingdatabase.core.domain.entity.PurchaseOrder

interface PurchaseOrderDataSourceGateway {

    fun findByUserId(userId: Int): List<PurchaseOrder>
    fun save(order: PurchaseOrder): PurchaseOrder
}