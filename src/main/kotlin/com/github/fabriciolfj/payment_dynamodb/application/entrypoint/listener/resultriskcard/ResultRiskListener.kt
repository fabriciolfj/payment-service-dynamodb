package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.listener.resultriskcard

import com.github.fabriciolfj.payment_dynamodb.application.entrypoint.listener.resultriskcard.CustomerResultRiskMapper.toEntity
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.riskcredit.RiskCustomerUseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ResultRiskListener(private val riskCustomerUseCase: RiskCustomerUseCase) {

    private val log = KotlinLogging.logger {  }

    @RabbitListener(queues = ["\${rabbit.risk.result}"])
    fun process(dto: CustomerResultRiskDTO) {
        log.info { "receive message result risk $dto" }
        riskCustomerUseCase.execute(toEntity(dto))
    }
}