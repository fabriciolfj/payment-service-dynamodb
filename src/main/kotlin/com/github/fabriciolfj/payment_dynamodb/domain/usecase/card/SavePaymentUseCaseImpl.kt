package com.github.fabriciolfj.payment_dynamodb.domain.usecase.card

import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class SavePaymentUseCaseImpl(private val adapter: SavePaymentCardAdapter) : SavePaymentCardUseCase {

    private val log = KotlinLogging.logger {}

    override fun execute(paymentCard: PaymentCard) {
        log.info { "saving payment card ${paymentCard.code}" }
        adapter.process(paymentCard)
    }
}