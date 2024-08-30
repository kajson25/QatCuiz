package com.example.mobilneprojekat_1.cats.ui_models


data class CatUiModel(
    // preview info
    val id: String,
    val name: String,
    val altNames: List<String>,
    val description: String,
    val temperament: List<String>,       // 3 for preview, all for details

    // detailed info
    val origins: String,
    val lifeSpan: String,
    val weight: String,           // imperial and metric - if in the api response
    val adaptability: Int,
    val affectionLevel: Int,
    val childFriendly: Int,
    val intelligence: Int,
    val sheddingLevel: Int,
    val rare: Int,                      // 1 for rare, 0 for not
    val wikipediaUrl: String,           // for the button hyperlink

    // images
    val imageUrl: String,               // GET imageUrl
)