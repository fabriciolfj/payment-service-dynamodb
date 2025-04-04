package com.github.fabriciolfj.payment_dynamodb.configuration.annotation

import org.springframework.stereotype.Service

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Service
annotation class UseCase