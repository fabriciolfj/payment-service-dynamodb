package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

import com.github.fabriciolfj.payment_dynamodb.application.adapter.card.PaymentCardDataMapper.toCreditCard
import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardRepository
import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.FindCardCreditByIdentifierAdapter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

@Component
class FindCardCreditByIdentifierAdapterImpl(private val repository: PaymentCardRepository) : FindCardCreditByIdentifierAdapter {

    private val log = KotlinLogging.logger {}

    override fun process(identifier: String): CreditCardPayment {
        return repository.findByIdentifier(identifier)
            .let {
                log.info { "found payment card $it" }
                toCreditCard(it)
            }
    }
}