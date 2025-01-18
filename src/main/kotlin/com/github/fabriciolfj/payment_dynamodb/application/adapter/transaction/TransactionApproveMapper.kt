package com.github.fabriciolfj.payment_dynamodb.application.adapter.transaction

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

object TransactionApproveMapper {

    fun toDto(entity: CreditCardPayment) = TransactionApproveDto(
        code = entity.code,
        identifier = entity.identifier
    )
}