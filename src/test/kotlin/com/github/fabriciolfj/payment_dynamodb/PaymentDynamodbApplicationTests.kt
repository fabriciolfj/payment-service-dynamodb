package com.github.fabriciolfj.payment_dynamodb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest



@ExtendWith(MockitoExtension::class, SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureWireMock(port = 0)
@Import(DynamodbClientLocalConfig::class, OrderClientConfig::class)
abstract class PaymentDynamodbApplicationTests {
	@LocalServerPort
	private var port: Int = 0

	protected lateinit var mvc: MockMvc

	protected fun setUp(controller: Any) {
		mvc = MockMvcBuilders.standaloneSetup(controller)
			.setControllerAdvice(MainExceptionHandler("https://localhost:$port"))
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