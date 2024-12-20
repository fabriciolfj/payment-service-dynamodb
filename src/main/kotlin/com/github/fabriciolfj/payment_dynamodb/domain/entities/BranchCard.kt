package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class BranchCard(val typeCard: TypeCard) {
    VISA(TypeCard.CREDIT),
    VISA_DEBIT(TypeCard.DEBIt),
    MASTERCARD_DEBIT(TypeCard.DEBIt),
    MASTERCARD(TypeCard.CREDIT)
}