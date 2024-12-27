package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard

import com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard.CreditCardPaymentMapper.toEntity
import com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.creditcard.CreditCardPaymentMapper.toResponse
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.FindCardCreditByIdentifier
import com.github.fabriciolfj.payment_dynamodb.domain.usecase.cardcredit.SavePaymentCardCreditUseCase
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/credit-card-payments")
class CreditCardPaymentController(private val savePaymentCardCreditUseCase: SavePaymentCardCreditUseCase,
                                  private val findCardCreditByIdentifier: FindCardCreditByIdentifier) {

    private val log = KotlinLogging.logger {}

    @PostMapping
    fun create(@Valid @RequestBody request: CreditCartRequest) {
        log.info { "payload receive create payment card credit $request" }

        val card = toEntity(request)
        savePaymentCardCreditUseCase.execute(card)
    }

    @GetMapping("/{identifier}")
    fun getByIdentifier(@NotEmpty @PathVariable identifier: String) : CreditCardResponse {
        log.info { "payload receive get payment card credit by identifier $identifier" }

        val result = findCardCreditByIdentifier.execute(identifier)
        return toResponse(result)
    }
}