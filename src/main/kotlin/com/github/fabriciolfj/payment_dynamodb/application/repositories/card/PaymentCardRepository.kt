package com.github.fabriciolfj.payment_dynamodb.application.repositories.card

import com.github.fabriciolfj.payment_dynamodb.constants.TabelaPaymentCardConstants.IDENTIFIER_INDEX
import com.github.fabriciolfj.payment_dynamodb.utils.DynamodbUtils.createKey
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException

@Repository
class PaymentCardRepository(private val table: DynamoDbTable<PaymentCardData>) {

    private val log = KotlinLogging.logger {}

    fun update(paymentCard: PaymentCardData) {
        val expression = Expression.builder().expression("attribute_exists(code)").build()

        val updateRequest = UpdateItemEnhancedRequest.builder(PaymentCardData::class.java)
            .conditionExpression(expression)
            .item(paymentCard)
            .build()

        try {
            table.updateItem(updateRequest)
        } catch (e: Exception) {
            throw e
        }
    }

    fun save(paymentCard: PaymentCardData) {
        val expression = Expression.builder().expression("attribute_not_exists(code)").build()

        val putItemRequest = PutItemEnhancedRequest.builder(PaymentCardData::class.java)
            .conditionExpression(expression)
            .item(paymentCard)
            .build()

        try {
            table.putItem(putItemRequest)
        } catch (e: ConditionalCheckFailedException) {
            log.warn { "payment id exits ${paymentCard.code}, generated new id ${paymentCard.customerId}" }
            val data = paymentCard.copy(code = paymentCard.customerId)
            save(data)
        } catch (e: Exception) {
            throw e
        }
    }

    fun findByIdentifier(identifier: String): PaymentCardData =
        table.index(IDENTIFIER_INDEX)
            .query(keyEqualTo(createKey(identifier)))
            .flatMap { it.items() }
            .let { items ->
                when {
                    items.isEmpty() -> throw NoSuchElementException("payment card with id $identifier not found")
                    items.size > 1 -> throw IllegalStateException("multiple payment cards found for identifier $identifier")
                    else -> items.first()
                }
            }
}