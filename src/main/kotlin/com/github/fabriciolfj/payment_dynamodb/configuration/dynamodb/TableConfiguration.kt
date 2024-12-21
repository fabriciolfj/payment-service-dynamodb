package com.github.fabriciolfj.payment_dynamodb.configuration.dynamodb

import com.github.fabriciolfj.payment_dynamodb.application.repositories.card.PaymentCardData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
class TableConfiguration {

    companion object {
        const val PAYMENT_CARD_TABLE = "PaymentCard"
    }

    @Bean
    fun paymentCardDataTable(client: DynamoDbEnhancedClient) : DynamoDbTable<PaymentCardData> {
        val schema = TableSchema.fromBean(PaymentCardData::class.java)
        return client.table(PAYMENT_CARD_TABLE, schema)
    }
}