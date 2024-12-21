package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardData
import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard

object PaymentCardDataMapper {

    fun toData(paymentCard: PaymentCard): PaymentCardData =
        PaymentCardData(paymentCard.code,
            paymentCard.getCustomerCode(),
            paymentCard.getValue(),
            paymentCard.getFlag(),
            paymentCard.getIdentifier())
}