package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardData
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

object PaymentCardDataMapper {

    fun toData(card: CreditCardPayment): PaymentCardData =
        PaymentCardData(card.code,
            card.customer,
            card.value,
            card.flagDescription,
            card.type,
            card.identifier,
            card.statusDescription)
}