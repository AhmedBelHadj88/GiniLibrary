package com.tda.ginilibrary.domain

import com.tda.ginilibrary.data.model.Response
import com.tda.ginilibrary.data.networking.CatImagesRepositoryImpl

internal class GetImageUseCase(var catImagesRepositoryImpl: CatImagesRepositoryImpl) :
    UseCase<Response, GetImageUseCase.Param>() {

    override suspend fun run(params: Param): Response {
        return catImagesRepositoryImpl.getImages(params.limit, params.page)
    }

    class Param(
        val limit: Int?,
        val page: Int?
    )

}