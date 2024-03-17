package api

import api.config.givenRestClient
import api.dto.PetDto
import api.dto.PetStatus
import api.dto.givenPetExists
import api.dto.giverPetTag
import io.github.serpro69.kfaker.Faker
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PetUpdateTests {
    private lateinit var existingPet: PetDto

    private val client = givenRestClient()
    private val faker = Faker()
    private val petName = faker.random.nextUUID()

    @BeforeEach
    fun beforeEach() {
        existingPet = givenPetExists(petName)
    }

    @AfterEach
    fun afterEach() {
        client.close()
    }

    @Test
    fun `pet can be updated in the store`() = runBlocking {
        val extraPetTag = giverPetTag(id = 2, name = "playful")
        val expectedPet = existingPet.copy(
            name = faker.animal.name(),
            status = PetStatus.PENDING,
            tags = ArrayList(existingPet.tags + extraPetTag),
            photoUrls = ArrayList(existingPet.photoUrls?.plus("photoUrl2") ?: arrayListOf("photoUrl")),
            category = existingPet.category?.copy(name = "dogs and cats")
        )

        val response = client.put("pet") {
            setBody(expectedPet)
        }
        val actualPet: PetDto = response.body()

        assertEquals(200, response.status.value)
        assertEquals(expectedPet, actualPet)
    }

    @Test
    fun `404 should be thrown if pet cannot be found`() = runBlocking {
        val fakeId = 0L
        val expectedPet = existingPet.copy(id = fakeId)

        val response = client.put("pet") {
            setBody(expectedPet)
        }

        assertEquals(404, response.status.value)
    }
}
