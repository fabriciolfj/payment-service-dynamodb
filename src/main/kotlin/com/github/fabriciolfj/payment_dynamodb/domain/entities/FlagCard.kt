package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class FlagCard(val typeCard: TypeCard, val description: String) {
    VISA(TypeCard.CREDIT, "visa"),
    VISA_DEBIT(TypeCard.DEBIt, "visaDebit"),
    MASTERCARD_DEBIT(TypeCard.DEBIt, "mastercardDebit"),
    MASTERCARD(TypeCard.CREDIT, "mastercard")
}