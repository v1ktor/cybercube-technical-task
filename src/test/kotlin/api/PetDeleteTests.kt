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

class PetDeleteTests {
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
    fun `pet can be deleted from the store`() = runBlocking {
        val (id) = expectedPet
        val expectedError = ErrorDto(
            code = 1,
            type = "error",
            message = "Pet not found"
        )

        var response = client.delete("pet/$id")

        assertEquals(200, response.status.value)

        response = client.get("pet/$id")
        val actualError: ErrorDto = response.body()

        assertEquals(404, response.status.value)
        assertEquals(expectedError, actualError)
    }

    @Test
    fun `404 is thrown if trying to delete non-existent pet`() = runBlocking {
        val fakeId = 0L

        val response = client.delete("pet/$fakeId")

        assertEquals(404, response.status.value)
    }
}
