package com.github.fabriciolfj.payment_dynamodb.application.adapter.card

data class NotifyRiskDto(val customer: String, val identifier: String)