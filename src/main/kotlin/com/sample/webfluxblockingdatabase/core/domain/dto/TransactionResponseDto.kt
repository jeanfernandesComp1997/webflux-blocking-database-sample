package com.sample.webfluxblockingdatabase.core.domain.dto

import com.sample.webfluxblockingdatabase.core.domain.enums.TransactionStatus

data class TransactionResponseDto(
    val userId: Int,
    val amount: Int,
    val status: TransactionStatus
)
