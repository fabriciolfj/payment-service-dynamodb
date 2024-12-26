package br.com.blz.subscription.configuration

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@TestConfiguration
class DynamodbClientLocalConfig(@Value("\${aws.dynamodb.endpoint}") private val endpoint: String,
                                @Value("\${aws.dynamodb.region}") private val region: String,
                                @Value("\${aws.dynamodb.secretKey}") private val secretKey: String,
                                @Value("\${aws.dynamodb.accessKey}") private val accessKey: String) {


    @Bean
    fun dynamoDBClient(awsCredentialsProviderChain: AwsCredentialsProviderChain) : DynamoDbClient {
        return DynamoDbClient.builder()
            .endpointOverride(URI.create(endpoint))
            .credentialsProvider(awsCredentialsProviderChain)
            .region(Region.of(region))
            .build()
    }

    @Bean
    fun awsCredentialsProviderChain() : AwsCredentialsProviderChain {
        val credentials = AwsBasicCredentials.create(accessKey, secretKey)
        return AwsCredentialsProviderChain.of(StaticCredentialsProvider.create(credentials))
    }

    @Bean
    fun dynamoDbEnhancedClient(dynamoDbClient: DynamoDbClient) : DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build()
    }

    @Bean
    fun dynamoDbTemplate(dynamoDbEnhancedClient: DynamoDbEnhancedClient) : DynamoDbTemplate {
        return DynamoDbTemplate(dynamoDbEnhancedClient)
    }

}
