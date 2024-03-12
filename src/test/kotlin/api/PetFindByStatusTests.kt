package api

import api.config.givenRestClient
import api.dto.PetDto
import api.dto.PetStatus
import api.dto.givenPetExists
import io.github.serpro69.kfaker.Faker
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PetFindByStatusTests {
    private lateinit var expectedPet: PetDto

    private val client = givenRestClient()
    private val faker = Faker()
    private val petName = faker.random.nextUUID()
    private val petStatus = PetStatus.SOLD

    @BeforeEach
    fun beforeEach() = runBlocking {
        expectedPet = givenPetExists(petName, petStatus)
    }

    @AfterEach
    fun afterEach() = runBlocking {
        client.close()
    }

    @Test
    fun `pet can be found by status`(): Unit = runBlocking {
        val response = client.get("pet/findByStatus") {
            parameter("status", petStatus.toString().lowercase())
        }
        val actualPets: List<PetDto> = response.body()

        assertEquals(200, response.status.value)
        assertThat(actualPets).contains(expectedPet)
    }
}
