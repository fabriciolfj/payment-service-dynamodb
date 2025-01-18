package com.github.fabriciolfj.payment_dynamodb.domain.usecase.riskcredit

import com.github.fabriciolfj.payment_dynamodb.configuration.annotation.UseCase
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CustomerResultRisk
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.FindCardCreditByIdentifierAdapter
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.transaction.TransactionNotifyRequestApproveAdapter
import io.github.oshai.kotlinlogging.KotlinLogging

@UseCase
class RiskCustomerUseCaseImpl(private val findCardCreditByIdentifierAdapter: FindCardCreditByIdentifierAdapter,
                              private val notify: TransactionNotifyRequestApproveAdapter
) : RiskCustomerUseCase {

    private val log = KotlinLogging.logger {  }

    override fun execute(risk: CustomerResultRisk) {
        if (risk.isApproved()) {
            val result = findCardCreditByIdentifierAdapter.process(risk.identifier)
            notify.process(result)
                .also {
                    log.info { "send notify request approve code ${result.code}, identify ${result.identifier}" }
                }

            return

        }

        log.info { "risk not approve to identifier ${risk.identifier}" }
    }
}