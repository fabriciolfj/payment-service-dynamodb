package com.github.fabriciolfj.payment_dynamodb.configuration.rabbitmq

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitQueueConfigRisk(@Value("\${rabbitmq.risk.queue}") private val queueName: String) {

    @Bean
    fun queueRisk() = Queue(queueName, true)

    @Bean
    fun exchangeRisk() = DirectExchange(queueName)

    @Bean
    fun bindingRisk() =
        BindingBuilder
            .bind(queueRisk())
            .to(exchangeRisk())
            .with(queueName)

}