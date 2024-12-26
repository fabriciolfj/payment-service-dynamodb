package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class FlagCardCredit(val description: String) {
    VISA("visa"),
    MASTERCARD( "mastercard");

    companion object {
        fun toFlagCardCredit(description: String): FlagCardCredit =
            entries.firstOrNull { it.description == description }
                ?: throw IllegalArgumentException("Invalid flag card credit description: $description")
    }
}