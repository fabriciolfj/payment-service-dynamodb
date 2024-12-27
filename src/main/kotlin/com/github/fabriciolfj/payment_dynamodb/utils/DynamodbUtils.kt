package com.github.fabriciolfj.payment_dynamodb.utils

import software.amazon.awssdk.enhanced.dynamodb.Key

object DynamodbUtils {

    fun createKey(id: String) = Key.builder().partitionValue(id).build()
}