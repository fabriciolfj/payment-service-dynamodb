package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardData
import com.github.fabriciolfj.payment_dynamodb.domain.entities.*

object PaymentCardDataMapper {

    fun toData(card: CreditCardPayment): PaymentCardData =
        PaymentCardData(card.code,
            card.customer,
            card.value,
            card.flagDescription,
            card.type,
            card.identifier,
            card.statusDescription,
            card.installments,
            card.datePayment)

    fun toCrediCard(data: PaymentCardData) = CreditCardPayment (
        code = data.code!!,
        installments = data.installments,
        flag = FlagCardCredit.toFlagCard(data.flag!!),
        payment = PaymentCard(
            payment = Payment(
                value = data.value!!,
                identifier = data.identifier!!,
                date = data.datePayment!!,
            ),
            customer = Customer(data.customerId!!),
            status = Status.toStatus(data.status!!),
        )
    )
}