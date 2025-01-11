package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.configuration.annotation.UseCase
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CustomerResultRisk
import io.github.oshai.kotlinlogging.KotlinLogging

@UseCase
class ProcessStatusCardCreditUseCase(private val findCardCreditByIdentifier: FindCardCreditByIdentifier,
                                     private val updateCardCreditAdapter: UpdateCardCreditAdapter,
                                     private val notifyApprovePaymentAdapter: NotifyApprovePaymentAdapter) {

    private val log = KotlinLogging.logger {  }

    fun execute(customerResultRisk: CustomerResultRisk) {
        val entity = findCardCreditByIdentifier.execute(customerResultRisk.identifier)

        if (customerResultRisk.isApproved()) {
            log.info { "card approved ${entity.code}" }
            notifyApprovePaymentAdapter.process(entity)
        }

        val result = entity.updateStatus(customerResultRisk.status)

        updateCardCreditAdapter.process(result)
        log.info { "updated status card ${result.code}" }
    }
}