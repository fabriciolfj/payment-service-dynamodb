package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard

import com.github.fabriciolfj.payment_dynamodb.domain.entities.*
import java.util.UUID

object CreditCardPaymentMapper {

    fun toEntity(request: CreditCartRequest) = CreditCardPayment(
        code = UUID.randomUUID().toString(),
        installments = request.installments!!,
        flag = FlagCardCredit.toFlagCard(request.flag!!),
        payment = PaymentCard(
            payment = Payment(
                value = request.value!!,
                identifier = request.identifier!!,
                date = request.date!!
            ),
            customer = Customer(request.customer!!),
            status = Status.PENDING
        )
    )

    fun toResponse(entity: CreditCardPayment) = CreditCardResponse(
        code = entity.code,
        date = entity.datePayment,
        customerId = entity.customer
    )
}