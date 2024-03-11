package api

import com.cybercube.api.config.givenRestClient
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
}
