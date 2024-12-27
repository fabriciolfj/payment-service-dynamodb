package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.Instant

data class CreditCartRequest(
    @field:NotEmpty(message = "{creditCard.identifier.empty}")
    val identifier: String?,
    @field:NotEmpty(message = "{creditCard.customer.empty}")
    val customer: String?,
    @field:NotNull(message = "{creditCard.value.empty}")
    val value: BigDecimal?,
    @field:NotNull(message = "{creditCard.installments.empty}")
    @field:Positive(message = "{creditCard.installments.positive}")
    val installments: Int?,
    @field:NotNull(message = "{creditCard.flag.empty}")
    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    val date: Instant?,
    @field:Pattern(regexp = "^(VISA|MASTERCARD)$", message = "{creditCard.flag.invalid}")
    val flag: String?)