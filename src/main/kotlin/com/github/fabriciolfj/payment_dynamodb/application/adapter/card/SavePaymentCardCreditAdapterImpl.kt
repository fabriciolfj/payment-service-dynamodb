package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.adapter.card.PaymentCardDataMapper.toData
import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardRepository
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.entities.PaymentCard
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.SavePaymentCardCreditAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class SavePaymentCardCreditAdapterImpl(private val repository: PaymentCardRepository) : SavePaymentCardCreditAdapter {

    private val log = KotlinLogging.logger {}

    override fun process(card: CreditCardPayment) {
        val data = toData(card)

        repository.save(data)
        log.info { "payment card saved ${card.code}" }
    }
}