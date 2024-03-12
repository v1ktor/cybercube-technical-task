package api.dto

import api.config.givenRestClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetDto(
    var id: Long? = null,
    var category: PetCategory? = PetCategory(),
    var name: String? = null,
    var photoUrls: ArrayList<String>? = arrayListOf(),
    var tags: ArrayList<PetTag> = arrayListOf(),
    var status: PetStatus? = null
)

@Serializable
data class PetCategory(
    var id: Int? = null,
    var name: String? = null
)

@Serializable
data class PetTag(
    var id: Int? = null,
    var name: String? = null
)

@Serializable
enum class PetStatus {
    @SerialName("available")
    AVAILABLE,

    @SerialName("pending")
    PENDING,

    @SerialName("sold")
    SOLD
}

fun givenPetCategory(): PetCategory {
    return PetCategory(1, "dogs")
}

fun giverPetTag(): PetTag {
    return PetTag(1, "friendly")
}

fun givenPetForCreate(name: String, status: PetStatus = PetStatus.AVAILABLE): PetDto {
    return PetDto(
        category = givenPetCategory(),
        name = name,
        photoUrls = arrayListOf("photoUrl"),
        tags = arrayListOf(giverPetTag()),
        status = status
    )
}

fun givenPetExists(name: String, status: PetStatus = PetStatus.AVAILABLE): PetDto {
    val client = givenRestClient()
    val pet = givenPetForCreate(name, status)

    val createdPet: PetDto = runBlocking {
        client.post("pet") {
            setBody(pet)
        }.body()
    }

    return createdPet
}
