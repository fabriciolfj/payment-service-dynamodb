package com.github.fabriciolfj.payment_dynamodb.domain.entities

import java.math.BigDecimal

data class Payment(
    val value: BigDecimal,
    val identifier: String)