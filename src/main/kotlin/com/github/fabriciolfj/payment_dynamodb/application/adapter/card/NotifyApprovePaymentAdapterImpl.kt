package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fabriciolfj.payment_dynamodb.application.adapter.card.NotifyApproveMapper.toDto
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.NotifyApprovePaymentAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class NotifyApprovePaymentAdapterImpl(private val kafkaTemplate: KafkaTemplate<String, String>,
                                      private val convertJson: ObjectMapper,
                                      @Value("\${kafka.topic.request-approve}") private val topic: String) : NotifyApprovePaymentAdapter{

    private val log = KotlinLogging.logger {  }

    override fun process(cardPayment: CreditCardPayment) {
        val json = convertJson.writeValueAsString(toDto(cardPayment))

        kafkaTemplate.send(topic, json)
        log.info { "message send $json" }
    }
}