package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

interface FindCardCreditByIdentifier {

    fun execute(identifier: String) : CreditCardPayment
}