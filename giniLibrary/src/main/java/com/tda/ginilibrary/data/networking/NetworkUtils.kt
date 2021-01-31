package com.tda.ginilibrary.data.networking

import com.tda.ginilibrary.data.model.Failure
import com.tda.ginilibrary.data.model.Response
import com.tda.ginilibrary.data.model.ErrorBodyModel
import kotlinx.coroutines.*
import retrofit2.Call

internal object NetworkUtils {
    suspend fun <T> request(
        call: Call<T>,
        transform: (T) -> Response,
        default: T?
    ): Response {
        return withContext(Dispatchers.IO) {
            try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> transform((response.body() ?: default!!))
                    false -> Response.FAILURE(Failure.FeatureFailure)
                }
            } catch (exception: Throwable) {
                Response.FAILURE(Failure.ServerError(ErrorBodyModel(exception.message)))
            }
        }
    }
}