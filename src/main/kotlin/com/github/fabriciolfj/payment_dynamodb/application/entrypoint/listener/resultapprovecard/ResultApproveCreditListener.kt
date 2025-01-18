package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.listener.resultapprovecard

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ResultApproveCreditListener {

    private val log = KotlinLogging.logger {  }

    @KafkaListener(topics = ["\${topic.kafka.result-approve}"], groupId = "\${kafka.group-id.payment}")
    fun receive(message: String) {
        log.info { "result approve credit $message" }
    }
}