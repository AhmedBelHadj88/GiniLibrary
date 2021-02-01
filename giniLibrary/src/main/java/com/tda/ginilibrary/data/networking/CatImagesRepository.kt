package com.tda.ginilibrary.data.networking

import com.tda.ginilibrary.data.model.Response

internal interface CatImagesRepository {

    suspend fun getImages(
        limit: Int?,
        page: Int?
    ): Response
}