package com.tda.ginilibrary.data.networking

import com.tda.ginilibrary.data.model.Image
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

internal interface CatService {

    @GET("/v1/images/search")
    fun getImages(
        @Query("limit") limit: Int?,
        @Query("page") page: Int?
    ): Call<List<Image>>

}