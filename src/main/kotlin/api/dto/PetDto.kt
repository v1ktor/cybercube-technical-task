package com.cybercube.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PetDto(
    var id: Long? = null,
    var category: PetCategory? = PetCategory(),
    var name: String? = null,
    var photoUrls: ArrayList<String>? = arrayListOf(),
    var tags: ArrayList<PetTags> = arrayListOf(),
    var status: PetStatus? = null
)

@Serializable
data class PetCategory(
    var id: Int? = null,
    var name: String? = null
)

@Serializable
data class PetTags(
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

fun giverPetTags(): PetTags {
    return PetTags(1, "friendly")
}

fun givenPetForCreate(name: String): PetDto {
    return PetDto(
        category = givenPetCategory(),
        name = name,
        photoUrls = arrayListOf("photoUrl"),
        tags = arrayListOf(giverPetTags()),
        status = PetStatus.AVAILABLE
    )
}
