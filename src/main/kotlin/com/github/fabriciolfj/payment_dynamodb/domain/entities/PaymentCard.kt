package com.github.fabriciolfj.payment_dynamodb.domain.entities


data class PaymentCard(val code: String,
                       val flagCard: FlagCard,
                       val payment: Payment)