package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.adapter.card.PaymentCardDataMapper.toData
import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardRepository
import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.card.SavePaymentCardAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SavePaymentCardAdapterImpl(private val repository: PaymentCardRepository) : SavePaymentCardAdapter {

    private val log = KotlinLogging.logger {}

    override fun process(paymentCard: PaymentCard) {
        val data = toData(paymentCard)

        repository.save(data)
        log.info { "payment card saved ${paymentCard.code}" }
    }
}