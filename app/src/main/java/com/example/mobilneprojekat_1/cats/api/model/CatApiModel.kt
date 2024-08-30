package com.example.mobilneprojekat_1.cats.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CatApiModel (
    val id: String? = "",
    val name: String? = "",
    val weight: Weight? = null,
    val life_span: String? = "",
    val bred_for: String? = "",
    val breed_group: String? = ""
)

@Serializable
data class Weight(
    val imperial: String?,
    val metric: String?
)

@Serializable
data class Image(
    @SerialName("url") val imageUrl: String
)