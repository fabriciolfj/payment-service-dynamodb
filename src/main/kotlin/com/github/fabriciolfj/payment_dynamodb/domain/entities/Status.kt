package com.github.fabriciolfj.payment_dynamodb.domain.entities

enum class Status(val description: String) {
    PENDING("pending"),
    PROCESS("process"),
    DENIED("denied"),
    APPROVED("approved");
}