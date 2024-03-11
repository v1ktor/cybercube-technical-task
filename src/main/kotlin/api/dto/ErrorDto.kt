package api.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val code: Int,
    val type: String,
    val message: String
)
