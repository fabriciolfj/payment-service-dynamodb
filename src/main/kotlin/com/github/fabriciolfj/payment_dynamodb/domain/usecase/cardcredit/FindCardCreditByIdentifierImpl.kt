package com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit

import com.github.fabriciolfj.payment_dynamodb.configuration.annotation.UseCase
import io.github.oshai.kotlinlogging.KotlinLogging

@UseCase
class FindCardCreditByIdentifierImpl(private val findCardCreditByIdentifierAdapter: FindCardCreditByIdentifierAdapter) : FindCardCreditByIdentifier {

    private val log = KotlinLogging.logger {}

    override fun execute(identifier: String) =
        findCardCreditByIdentifierAdapter.process(identifier)
        .also { log.info { "result $it" } }
}