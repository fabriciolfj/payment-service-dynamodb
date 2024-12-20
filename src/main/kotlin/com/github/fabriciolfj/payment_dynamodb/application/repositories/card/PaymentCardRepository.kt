package com.github.fabriciolfj.payment_dynamodb.application.repositories.card

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException

@Repository
class PaymentCardRepository(private val table: DynamoDbTable<PaymentCardData>) {

    private val log = KotlinLogging.logger {}

    fun save(paymentCard: PaymentCardData) {
        val expression = Expression.builder().expression("attribute_not_exists(id)").build()

        val putItemRequest = PutItemEnhancedRequest.builder(PaymentCardData::class.java)
            .conditionExpression(expression)
            .item(paymentCard)
            .build()

        try {
            table.putItem(putItemRequest)
        } catch (e: ConditionalCheckFailedException) {
            val data = paymentCard.copy(code = paymentCard.customer)
            save(data)
        } catch (e: Exception) {
            throw e
        }
    }
}