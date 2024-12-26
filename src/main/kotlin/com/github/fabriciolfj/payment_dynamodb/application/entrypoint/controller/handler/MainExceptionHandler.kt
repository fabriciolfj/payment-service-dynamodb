package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.handler

import com.github.fabriciolfj.payment_dynamodb.domain.exceptions.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MainExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ApiError {
        val errors = ex.bindingResult.fieldErrors.map {
            ApiError.FieldError(it.field, it.defaultMessage ?: "Invalid field")
        }

        return ApiError(HttpStatus.BAD_REQUEST.value(), "Validation Error", errors)
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleBusinessException(ex: BusinessException): ApiError {
        return ApiError(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.message ?: "Business Error")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericError(ex: Exception): ApiError {
        return ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            title = "Internal Server Error"
        )
    }
}