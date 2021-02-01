package com.tda.ginilibrary

import com.tda.ginilibrary.core.AppContainer
import com.tda.ginilibrary.data.mapper.ImageToCatImageMapper
import com.tda.ginilibrary.data.model.Image
import com.tda.ginilibrary.data.model.Response
import com.tda.ginilibrary.data.networking.CatImagesRepositoryImpl
import com.tda.ginilibrary.data.networking.RetrofitService
import com.tda.ginilibrary.domain.GetImageUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class DataTest {

    private lateinit var getImageUseCase: GetImageUseCase

    private lateinit var dataRepository: CatImagesRepositoryImpl

    private lateinit var retrofitService: RetrofitService

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val rt = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL.toHttpUrl())
            .client(AppContainer().provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = RetrofitService(rt)
        dataRepository = CatImagesRepositoryImpl(retrofitService)
        getImageUseCase = GetImageUseCase(dataRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testMappingData() = runBlocking {
        getImageUseCase(GetImageUseCase.Param(30, 1)) {
            when (it) {
                is Response.SUCCESS -> {
                    val imageList = it.data as List<Image>
                    val map = ImageToCatImageMapper().map(imageList)
                    assertEquals(map.size, 30)
                    assertTrue(map.firstOrNull()?.url?.isNotEmpty() ?: false)
                }
                is Response.FAILURE -> assert(false)
            }
        }
    }

    @Test
    fun testUseCase() = runBlocking {
        getImageUseCase(GetImageUseCase.Param(30, 1)) {
            when (it) {
                is Response.SUCCESS -> {
                    val imageList = it.data as List<Image>
                    assertTrue(imageList.isNotEmpty())
                }
                is Response.FAILURE -> assert(false)
            }
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}