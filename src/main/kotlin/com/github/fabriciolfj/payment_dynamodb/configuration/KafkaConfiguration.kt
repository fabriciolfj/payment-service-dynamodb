package com.github.fabriciolfj.payment_dynamodb.configuration

import io.micrometer.common.KeyValues
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.ssl.DefaultSslBundleRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.micrometer.KafkaRecordSenderContext
import org.springframework.kafka.support.micrometer.KafkaTemplateObservationConvention

@Configuration
@EnableKafka
class KafkaConfiguration(private val kafkaProperties: KafkaProperties) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val props = kafkaProperties.buildConsumerProperties(DefaultSslBundleRegistry())
        props.remove(ConsumerConfig.CLIENT_ID_CONFIG)

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<*, *> {
        return ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            setConsumerFactory(consumerFactory())
            containerProperties.isObservationEnabled = true
            containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        }
    }

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, String>): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory).apply {
            this.setObservationEnabled(true)
            this.setObservationConvention(defaultObservationConvention())
        }
    }

    private fun defaultObservationConvention() = object : KafkaTemplateObservationConvention {
        override fun getLowCardinalityKeyValues(context: KafkaRecordSenderContext): KeyValues {
            return KeyValues.of(
                "topic", context.destination,
                "id", context.record.key().toString()
            )
        }
    }
}