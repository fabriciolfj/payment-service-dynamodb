package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class FlagCard(val typeCard: TypeCard) {
    VISA(TypeCard.CREDIT),
    VISA_DEBIT(TypeCard.DEBIt),
    MASTERCARD_DEBIT(TypeCard.DEBIt),
    MASTERCARD(TypeCard.CREDIT)
}