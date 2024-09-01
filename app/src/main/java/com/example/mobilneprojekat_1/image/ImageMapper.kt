package com.example.mobilneprojekat_1.image

import com.example.mobilneprojekat_1.database.entities.ImageDbModel
import com.example.mobilneprojekat_1.image.image_ui.ImageUiModel


fun ImageApiModel.asImageDbModel(catId: String): ImageDbModel {
    return ImageDbModel(
        id = id,
        catId = catId,
        url = url,
        width = width,
        height = height
    )
}

fun ImageDbModel.asImageUiModel(): ImageUiModel {
    return ImageUiModel(
        id = id,
        url = url,
        width = width,
        height = height
    )
}