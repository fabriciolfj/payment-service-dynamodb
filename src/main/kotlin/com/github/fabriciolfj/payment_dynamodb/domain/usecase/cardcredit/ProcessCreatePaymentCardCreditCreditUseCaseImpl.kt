package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.configuration.annotation.UseCase
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import io.github.oshai.kotlinlogging.KotlinLogging

@UseCase
class ProcessCreatePaymentCardCreditCreditUseCaseImpl(private val savePaymentCardCreditUseCase: SavePaymentCardCreditUseCase,
                                                      private val notifyPaymentCardCreditAdapter: NotifyPaymentCardCreditAdapter) : ProcessCreatePaymentCardCreditUseCase {

    private val log = KotlinLogging.logger {  }

    override fun execute(entity: CreditCardPayment) {
        savePaymentCardCreditUseCase.execute(entity)
            .also {
                notifyPaymentCardCreditAdapter.execute(entity)
                log.info { "process finished card ${entity.code}" }
            }
    }
}