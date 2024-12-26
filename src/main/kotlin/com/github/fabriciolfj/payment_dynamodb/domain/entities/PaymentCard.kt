package com.github.fabriciolfj.payment_dynamodb.domain.entities


data class PaymentCard(private val payment: Payment,
                       private val customer: Customer,
                       private val status: Status) {

    val customerCode = this.customer.code

    val value = this.payment.value

    val identifier = this.payment.identifier

    val statusDescription = this.status.description
}