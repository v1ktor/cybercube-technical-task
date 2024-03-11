package api

import com.cybercube.api.config.givenRestClient
import com.cybercube.api.dto.PetDto
import com.cybercube.api.dto.givenPetForCreate
import io.github.serpro69.kfaker.Faker
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class PetCreateTests {
    private lateinit var expectedPet: PetDto;

    private val client = givenRestClient()
    private val faker = Faker()
    private val petName = faker.random.nextUUID()

    @BeforeEach
    fun beforeEach() {
        expectedPet = givenPetForCreate(petName)
    }

    @AfterEach
    fun afterEach() = runBlocking {
        client.close()
    }

    @Test
    fun `add new pet to the store`(): Unit = runBlocking {
        val response = client.post("pet") {
            setBody(expectedPet);
        }

        val actualPet: PetDto = response.body()

        assertEquals(200, response.status.value)
        assertThat(actualPet)
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(expectedPet);
    }

    @Test
    @Disabled("The documentation says that the name is required, but the server does not enforce it. Possible bug.")
    fun `pet without name cannot be added to the store`() = runBlocking {
        expectedPet.name = null

        val response = client.post("pet") {
            setBody(expectedPet);
        }

        assertEquals(400, response.status.value)
    }

    @Test
    @Disabled("The documentation says that the photoUrls is required, but the server does not enforce it. Possible bug.")
    fun `pet without photos cannot be added to the store`() = runBlocking {
        expectedPet.photoUrls = null

        val response = client.post("pet") {
            setBody(expectedPet);
        }

        assertEquals(400, response.status.value)
    }
}
