package com.tda.ginilibrary.core

import com.tda.ginilibrary.BuildConfig
import com.tda.ginilibrary.data.networking.CatImagesRepositoryImpl
import com.tda.ginilibrary.data.networking.RetrofitService
import com.tda.ginilibrary.domain.GetImageUseCase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class AppContainer {

    var allCatsViewModelFactory: AllCatsViewModelFactory

    init {
        allCatsViewModelFactory = AllCatsViewModelFactory(
            GetImageUseCase(
                CatImagesRepositoryImpl(
                    RetrofitService(provideApi())
                )
            )
        )
    }

    private fun provideApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .run {
            addInterceptor(provideLoggingInterceptor())
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            addInterceptor(provideInterceptorsHeaders())
        }.build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

    private fun provideInterceptorsHeaders(): Interceptor = Interceptor.invoke {
        val requestBuilder =
            it.request().newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("x-api-key", BuildConfig.APP_KEY)
        it.proceed(requestBuilder.build())
    }

}