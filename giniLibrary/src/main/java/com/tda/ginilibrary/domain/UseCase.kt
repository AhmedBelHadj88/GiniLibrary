package com.tda.ginilibrary.domain

import com.tda.ginilibrary.data.model.Response
import kotlinx.coroutines.*

internal abstract class UseCase<out Type, in Params> where Type : Any {

    private val job = SupervisorJob()
    private val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    abstract suspend fun run(params: Params): Response

    suspend operator fun invoke(params: Params, onResult: (Response) -> Unit = {}) {

        withContext(bgDispatcher) {
            val job = run(params)
            uiScope.launch {
                onResult(job)
            }
        }
    }

    class None
}
