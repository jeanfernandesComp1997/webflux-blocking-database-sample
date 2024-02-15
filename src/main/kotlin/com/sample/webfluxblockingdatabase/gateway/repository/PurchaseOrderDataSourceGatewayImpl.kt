package com.sample.webfluxblockingdatabase.gateway.repository

import com.sample.webfluxblockingdatabase.core.domain.entity.PurchaseOrder
import com.sample.webfluxblockingdatabase.core.gateway.PurchaseOrderDataSourceGateway
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseOrderDataSourceGatewayImpl : PurchaseOrderDataSourceGateway, JpaRepository<PurchaseOrder, Int> {

    override fun findByUserId(userId: Int): List<PurchaseOrder>
}