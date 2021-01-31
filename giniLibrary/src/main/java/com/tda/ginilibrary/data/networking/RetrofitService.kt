package com.tda.ginilibrary.data.networking

import com.tda.ginilibrary.data.model.Image
import retrofit2.Call
import retrofit2.Retrofit

internal class RetrofitService(retrofit: Retrofit) : CatService {

    private val catApi by lazy { retrofit.create(CatService::class.java) }

    override fun getImages(limit: Int?, page: Int?): Call<List<Image>> {
        return catApi.getImages(limit, page)
    }
}