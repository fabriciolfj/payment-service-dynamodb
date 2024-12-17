package com.github.fabriciolfj.payment_dynamodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentDynamodbApplication

fun main(args: Array<String>) {
	runApplication<PaymentDynamodbApplication>(*args)
}
