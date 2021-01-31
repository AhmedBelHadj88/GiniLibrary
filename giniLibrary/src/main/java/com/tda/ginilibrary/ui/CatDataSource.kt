package com.tda.ginilibrary.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.tda.ginilibrary.data.mapper.CatImage
import com.tda.ginilibrary.data.mapper.ImageToCatImageMapper
import com.tda.ginilibrary.data.model.Image
import com.tda.ginilibrary.data.model.Response
import com.tda.ginilibrary.domain.GetImageUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class CatDataSource(
    private val getImageUseCase: GetImageUseCase,
    private val viewModelScope: CoroutineScope
) :
    PageKeyedDataSource<Int, CatImage>() {

    var state = MutableLiveData<LoadingState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CatImage>
    ) {
        setState(LoadingState.LOADING)
        viewModelScope.launch {
            getImageUseCase(GetImageUseCase.Param(params.requestedLoadSize, 1)) {
                when (it) {
                    is Response.SUCCESS -> {
                        setState(LoadingState.DONE)
                        callback.onResult(

                            ImageToCatImageMapper().map(it.data as List<Image>),
                            null,
                            2
                        )
                    }
                    is Response.FAILURE -> setState(LoadingState.ERROR(it.failure))
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CatImage>) {
        setState(LoadingState.LOADING)
        viewModelScope.launch {
            getImageUseCase(GetImageUseCase.Param(params.requestedLoadSize, params.key)) {
                when (it) {
                    is Response.SUCCESS -> {
                        callback.onResult(
                            ImageToCatImageMapper().map(it.data as List<Image>),
                            params.key + 1
                        )
                    }
                    is Response.FAILURE -> setState(LoadingState.ERROR(it.failure))
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CatImage>) {

    }

    private fun setState(state: LoadingState) {
        this.state.postValue(state)
    }
}