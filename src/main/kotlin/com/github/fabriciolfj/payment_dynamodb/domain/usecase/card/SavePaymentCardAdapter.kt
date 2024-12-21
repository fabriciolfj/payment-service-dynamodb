package com.github.fabriciolfj.payment_dynamodb.domain.usecase.card

import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard

interface SavePaymentCardAdapter {

    fun process(paymentCard: PaymentCard)
}