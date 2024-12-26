package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class SavePaymentCreditUseCaseImpl(private val adapter: SavePaymentCardCreditAdapter) : SavePaymentCardCreditUseCase {

    private val log = KotlinLogging.logger {}

    override fun execute(card: CreditCardPayment) {
        log.info { "saving payment card ${card.code}" }
        adapter.process(card)
    }
}