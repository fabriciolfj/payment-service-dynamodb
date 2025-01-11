package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

object NotifyApproveMapper {

    fun toDto(cardPayment: CreditCardPayment) : NotifyApproveDto {
        return NotifyApproveDto(
            code = cardPayment.code,
            identifier = cardPayment.identifier
        )
    }
}