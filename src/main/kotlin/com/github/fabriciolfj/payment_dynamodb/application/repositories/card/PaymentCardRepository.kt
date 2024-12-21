package com.github.fabriciolfj.payment_dynamodb.application.repositories.card

import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable

@Repository
class PaymentCardRepository(private val table: DynamoDbTable<PaymentCardData>) {
}