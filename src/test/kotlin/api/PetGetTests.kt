package api

import api.config.givenRestClient
import api.dto.ErrorDto
import api.dto.PetDto
import api.dto.givenPetExists
import io.github.serpro69.kfaker.Faker
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PetGetTests {
    private lateinit var expectedPet: PetDto

    private val client = givenRestClient()
    private val faker = Faker()
    private val petName = faker.random.nextUUID()

    @BeforeEach
    fun beforeEach() {
        expectedPet = givenPetExists(petName)
    }

    @AfterEach
    fun afterEach() {
        client.close()
    }

    @Test
    fun `pet data can be accessed by id`() = runBlocking {
        val (id) = expectedPet

        val response = client.get("pet/$id")
        val actualPet: PetDto = response.body()

        assertEquals(200, response.status.value)
        assertEquals(expectedPet, actualPet)
    }

    @Test
    fun `error is thrown if pet is accessed by non-existent id`() = runBlocking {
        val fakeId = 0L
        val expectedError = ErrorDto(
            code = 1,
            type = "error",
            message = "Pet not found"
        )

        val response = client.get("pet/$fakeId")
        val actualError: ErrorDto = response.body()

        assertEquals(404, response.status.value)
        assertEquals(expectedError, actualError)
    }
}
