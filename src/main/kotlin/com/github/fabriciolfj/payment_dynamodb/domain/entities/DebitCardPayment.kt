package com.github.fabriciolfj.payment_dynamodb.domain.entities

data class DebitCardPayment(val code: String,
                            val flag: FlagCardDebit,
                            private val payment: PaymentCard) {

    val value = this.payment.value

    val identifier = this.payment.identifier

    val statusDescription = this.payment.statusDescription

    val customer = this.payment.customerCode

    val type = TypeCard.DEBIt
}