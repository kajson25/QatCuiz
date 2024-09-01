package com.example.mobilneprojekat_1.cat

import com.example.mobilneprojekat_1.cat.cats_ui.CatUiModel
import com.example.mobilneprojekat_1.database.entities.CatDbModel

fun CatApiModel.asCatDbModel(): CatDbModel {
    return CatDbModel(
        id = id,
        name = name?: "",
        altNames = altNames?: "",
        description = description?: "",
        temperament = temperament?: "",
        origin = origin?: "",
        lifeSpan = lifeSpan?: "",
        weightImperial = weight?.imperial ?: "",
        weightMetric = weight?.metric ?: "",

        // traits
        adaptability = adaptability ?: 0,
        affectionLevel = affectionLevel ?: 0,
        childFriendly = childFriendly ?: 0,
        intelligence = intelligence,
        sheddingLevel = sheddingLevel ?: 0,
        strangerFriendly = strangerFriendly ?: 0,
        rare = rare ?: -1,

        wikipediaUrl = wikipediaUrl,
        imageUrl = image?.imageUrl ?: ""
    )
}

fun CatDbModel.asCatUiModel(): CatUiModel {
    return CatUiModel(
        id = id,
        name = name,
        altNames = altNames.split(", "),
        description = description,
        temperament = temperament.split(", "),
        //origins = origin.split(", "),
        origins = origin,
        lifeSpan = lifeSpan,
        //weight = listOf(weightImperial, weightMetric),
        weight = "$weightImperial $weightMetric",
        adaptability = adaptability,
        affectionLevel = affectionLevel,
        childFriendly = childFriendly,
        intelligence = intelligence,
        sheddingLevel = sheddingLevel,
        rare = rare,
        wikipediaUrl = wikipediaUrl,
        imageUrl = imageUrl
    )
}