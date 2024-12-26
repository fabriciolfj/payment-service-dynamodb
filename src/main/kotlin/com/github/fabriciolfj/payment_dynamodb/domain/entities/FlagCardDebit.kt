package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class FlagCardDebit(val description: String) {
    VISA("visa"),
    MASTERCARD("mastercard")
}