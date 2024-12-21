package com.github.fabriciolfj.payment_dynamodb.domain.entities


data class PaymentCard(val code: String,
                       val flagCard: FlagCard,
                       val payment: Payment,
                       val customer: Customer) {

    fun getCustomerCode() = this.customer.code

    fun getValue() = this.payment.value

    fun getFlag() = this.flagCard.description

    fun getIdentifier() = this.payment.identifier
}