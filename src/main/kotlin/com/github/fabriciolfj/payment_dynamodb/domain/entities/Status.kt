package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class Status(val description: String) {
    PENDING("pending"),
    PROCESS("process"),
    DENIED("denied"),
    APPROVED("approved");

    companion object {
        fun toStatus(description: String): Status =
            entries.firstOrNull { it.description.equals(description, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid status description: $description")
    }
}