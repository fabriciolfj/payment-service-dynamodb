package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.listener.resultriskcard

data class CustomerResultRiskDTO(val status: String, val transactionId: String, val identifier: String)