package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard

import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.SavePaymentCardCreditUseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/credit-card-payments")
class CreditCardPaymentController(private val savePaymentCardCreditUseCase: SavePaymentCardCreditUseCase) {

    private val log = KotlinLogging.logger {}

    @PostMapping
    fun create(@Valid @RequestBody request: CreditCartRequest) {
        log.info { "payload receive create payment card credit $request" }

        val card = CreditCardPaymentMapper.toEntity(request)
        savePaymentCardCreditUseCase.execute(card)
    }
}