package com.tda.ginilibrary.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tda.ginilibrary.data.mapper.CatImage
import com.tda.ginilibrary.domain.GetImageUseCase
import kotlinx.coroutines.CoroutineScope

internal class CatDataSourceFactory(
    private var getImageUseCase: GetImageUseCase,
    private var coroutineScope: CoroutineScope
) :
    DataSource.Factory<Int, CatImage>() {
    val catDataSourceLiveData = MutableLiveData<CatDataSource>()
    override fun create(): DataSource<Int, CatImage> {
        val dataSource = CatDataSource(getImageUseCase, coroutineScope)
        catDataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}