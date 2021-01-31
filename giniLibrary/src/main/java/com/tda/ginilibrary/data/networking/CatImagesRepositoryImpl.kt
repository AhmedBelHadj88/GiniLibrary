package com.tda.ginilibrary.data.networking

import com.tda.ginilibrary.data.model.Response

internal class CatImagesRepositoryImpl(private val service: RetrofitService) : CatImagesRepository {

    override suspend fun getImages(limit: Int?, page: Int?): Response {
        return NetworkUtils.request(
            service.getImages(limit, page),
            { Response.SUCCESS(it.toList()) },
            arrayListOf()
        )
    }
}