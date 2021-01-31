package com.tda.ginilibrary.ui

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tda.ginilibrary.data.mapper.CatImage
import com.tda.ginilibrary.domain.GetImageUseCase

internal class AllCatsViewModel(getImageUseCase: GetImageUseCase) : ViewModel() {

    private var catDataSourceFactory: CatDataSourceFactory =
        CatDataSourceFactory(getImageUseCase, viewModelScope)

    var pagingList: LiveData<PagedList<CatImage>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(15)
            .setInitialLoadSizeHint(30)
            .setEnablePlaceholders(false)
            .build()
        pagingList = LivePagedListBuilder(catDataSourceFactory, config).build()
    }

    fun getLoadingState(): LiveData<LoadingState> {
        return Transformations.switchMap(
            catDataSourceFactory.catDataSourceLiveData,
            CatDataSource::state
        )
    }

}