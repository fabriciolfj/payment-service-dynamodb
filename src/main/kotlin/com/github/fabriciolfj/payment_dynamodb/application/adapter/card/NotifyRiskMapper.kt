package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

object NotifyRiskMapper {

    fun toDto(entity: CreditCardPayment)  = NotifyRiskDto(
        customer = entity.customer,
        identifier = entity.identifier
    )
}