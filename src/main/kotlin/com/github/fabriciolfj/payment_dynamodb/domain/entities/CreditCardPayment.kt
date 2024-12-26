package com.github.fabriciolfj.payment_dynamodb.domain.entities

data class CreditCardPayment(val code: String,
                             val installments: Int,
                             private val flag: FlagCardCredit,
                             private val payment: PaymentCard) {

    val value = this.payment.value

    val flagDescription = this.flag.description

    val identifier = this.payment.identifier

    val statusDescription = this.payment.statusDescription

    val customer = this.payment.customerCode

    val type = TypeCard.CREDIT.name
}