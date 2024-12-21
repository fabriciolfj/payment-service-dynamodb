package com.github.fabriciolfj.payment_dynamodb.domain.usecase.card

import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard

interface SavePaymentCardUseCase {

    fun execute(paymentCard: PaymentCard)
}