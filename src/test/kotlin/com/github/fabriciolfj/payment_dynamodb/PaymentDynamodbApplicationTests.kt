package com.github.fabriciolfj.payment_dynamodb

import br.com.blz.subscription.configuration.DynamodbClientLocalConfig
import com.github.fabriciolfj.payment_dynamodb.application.entrypoint.controller.handler.MainExceptionHandler
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.testcontainers.containers.ComposeContainer
import org.testcontainers.containers.wait.strategy.Wait.forLogMessage
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors


@ExtendWith(MockitoExtension::class, SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [PaymentDynamodbApplicationTests::class])
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWireMock(port = 0)
@Import(DynamodbClientLocalConfig::class)
abstract class PaymentDynamodbApplicationTests {
	@LocalServerPort
	private var port: Int = 0

	protected lateinit var mvc: MockMvc

	protected fun setUp(controller: Any) {
		mvc = MockMvcBuilders.standaloneSetup(controller)
			.setControllerAdvice(MainExceptionHandler())
			.build()
	}

	protected fun readRequestFileMapping(fileName: String) : String {
		return readFileMapping(fileName, "requests")
	}

	protected fun readResponseFileMapping(fileName: String) : String {
		return readFileMapping(fileName, "responses")
	}

	private fun readFileMapping(fileName: String, filePath: String) : String {
		val inputStream = this.javaClass.classLoader.getResourceAsStream("$filePath/$fileName")
		val fileString = convert(inputStream)
		val result = StringUtils.replace(fileString, "\n", "")

		return StringUtils.normalizeSpace(result)
	}

	private fun convert(inputStream: InputStream) : String {
		val reader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
		return BufferedReader(reader).lines()
			.collect(Collectors.joining("\n"))
	}

	companion object {

		@Suppress("UNUSED")
		private val DOCKER_COMPOSER = ComposeContainer(File("docker-compose.yml")).apply {
			waitingFor("localstack", forLogMessage(".*(Dynamo|SecretsManager) Initialized!\n", 2))
			start()
			withEnv("AWS_ACCESS_KEY", "dummy-access-key")
			withEnv("AWS_SECRET_KEY", "dummy-secret-key")
			println("Docker Compose Started!")
		}
	}
}