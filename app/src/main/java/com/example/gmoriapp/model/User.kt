package com.example.gmoriapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class GMoriAPIResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("image")
    val image: Image,
    @field:SerializedName("description")
    val description: String?,
)

data class Image(
    @field:SerializedName("url")
    val url: String?
)

@Serializable
data class User(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val description: String
)

fun GMoriAPIResponse.toUser(): User {
    return User(
        id = id,
        name = name,
        imageUrl = ("https://" + image.url),
        description = description ?: "",
    )
}
