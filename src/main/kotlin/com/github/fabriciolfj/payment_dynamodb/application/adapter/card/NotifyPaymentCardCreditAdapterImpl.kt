package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.adapter.card.NotifyRiskMapper.toDto
import com.github.fabriciolfj.payment_dynamodb.configuration.annotation.UseCase
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.NotifyPaymentCardCreditAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value

@UseCase
class NotifyPaymentCardCreditAdapterImpl(private val rabbitTemplate: RabbitTemplate,
                                         @Value("\${rabbit.risk.queue}") private val queue: String) : NotifyPaymentCardCreditAdapter {

    private val log = KotlinLogging.logger {  }

    override fun execute(entity: CreditCardPayment) {
        rabbitTemplate.convertAndSend(queue, toDto(entity))
            .also {
                log.info { "send success message to risk ${entity.code}" }
            }
    }
}