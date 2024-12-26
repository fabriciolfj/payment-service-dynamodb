package com.github.fabriciolfj.payment_dynamodb.domain.entities

import java.math.BigDecimal
import java.time.Instant

data class Payment(
    val value: BigDecimal,
    val identifier: String,
    val date: Instant)