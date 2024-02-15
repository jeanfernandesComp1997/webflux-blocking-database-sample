package com.sample.webfluxblockingdatabase.core.domain.dto


class RequestContext(val purchaseOrderRequestDto: PurchaseOrderRequestDto) {
    var productDto: ProductDto? = null
    var transactionRequestDto: TransactionRequestDto? = null
    var transactionResponseDto: TransactionResponseDto? = null
}