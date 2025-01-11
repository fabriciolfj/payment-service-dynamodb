package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

interface UpdateCardCreditAdapter {

    fun process(entity: CreditCardPayment)
}