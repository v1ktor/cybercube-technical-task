package api

import com.cybercube.api.config.givenRestClient
import com.cybercube.api.dto.ErrorDto
import com.cybercube.api.dto.PetDto
import com.cybercube.api.dto.givenPetExists
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
    fun beforeEach() = runBlocking {
        expectedPet = givenPetExists(petName)
    }

    @AfterEach
    fun afterEach() = runBlocking {
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
