package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class FlagCardDebit(val description: String) {
    VISA("visa"),
    MASTERCARD("mastercard");

    companion object {
        fun toFlagCard(description: String): FlagCardDebit =
            entries.firstOrNull { it.description.equals(description, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid flag card debit description: $description")
    }
}