package com.sample.webfluxblockingdatabase.core.domain.dto


class RequestContext(private val purchaseOrderRequestDto: PurchaseOrderRequestDto) {
    private val productDto: ProductDto? = null
    private val transactionRequestDto: TransactionRequestDto? = null
    private val transactionResponseDto: TransactionResponseDto? = null
}