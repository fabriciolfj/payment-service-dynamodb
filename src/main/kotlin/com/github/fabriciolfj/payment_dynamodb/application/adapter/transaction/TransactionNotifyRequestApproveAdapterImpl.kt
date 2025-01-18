package com.github.fabriciolfj.payment_dynamodb.application.adapter.transaction

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.fabriciolfj.payment_dynamodb.application.adapter.transaction.TransactionApproveMapper.toDto
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.transaction.TransactionNotifyRequestApproveAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class TransactionNotifyRequestApproveAdapterImpl(private val kafkaTemplate: KafkaTemplate<String, String>,
                                                 private val objetMapper: ObjectMapper,
                                                 @Value("\${topic.kafka.request-approve}")
                                                 private val topic: String) : TransactionNotifyRequestApproveAdapter {

    private val log = KotlinLogging.logger {  }

    override fun process(entity: CreditCardPayment) {
        val dto = toDto(entity)

        objetMapper.writeValueAsString(dto)
            .also {
                log.info { "message prepare $it, init send topic $topic"}
                kafkaTemplate.send(topic, it)
            }
    }
}