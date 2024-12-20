package com.github.fabriciolfj.payment_dynamodb.configuration.dynamodb

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamodbClientConfig(@Value("\${aws.dynamodb.endpoint}") private val endpoint: String,
                           @Value("\${aws.dynamodb.region}") private val region: String,
                           @Value("\${aws.dynamodb.accesskey}") private val accessKey: String,
                           @Value("\${aws.dynamodb.secretkey}") private val secretKey: String) {

    @Bean
    fun dynamoDBClient(awsCredentialsProviderChain: AwsCredentialsProviderChain) : DynamoDbClient {
        return DynamoDbClient.builder()
            .region(Region.of(region))
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(awsCredentialsProviderChain)
            .build()
    }

    @Bean
    fun awsCredentialsProviderChain(): AwsCredentialsProviderChain {
        val credentials = AwsBasicCredentials.create(accessKey, secretKey)
        return AwsCredentialsProviderChain.of(
            StaticCredentialsProvider.create(credentials))
    }

    @Bean
    fun dynamoDbEnhancedClient(dynamoDBClient: DynamoDbClient) = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(dynamoDBClient)
        .build()

    @Bean
    fun dynamoDBTemplate(dynamoDbEnhancedClient: DynamoDbEnhancedClient) = DynamoDbTemplate(dynamoDbEnhancedClient)
}