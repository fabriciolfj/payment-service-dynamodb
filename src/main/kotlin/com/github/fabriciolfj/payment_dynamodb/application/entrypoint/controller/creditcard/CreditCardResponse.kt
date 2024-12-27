package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard

import java.time.Instant

data class CreditCardResponse(val code: String, val date: Instant, val customerId: String)