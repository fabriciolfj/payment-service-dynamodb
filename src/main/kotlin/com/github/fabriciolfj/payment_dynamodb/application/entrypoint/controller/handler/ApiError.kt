package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.handler

data class ApiError(
    val status: Int,
    val title: String,
    val errors: List<FieldError> = emptyList()
) {
    data class FieldError(
        val field: String,
        val message: String
    )
}