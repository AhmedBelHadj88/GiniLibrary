package com.tda.ginilibrary.core

import com.tda.ginilibrary.domain.GetImageUseCase
import com.tda.ginilibrary.ui.AllCatsViewModel

internal interface Factory<T> {
    fun create(): T
}

internal class AllCatsViewModelFactory(private val getImageUseCase: GetImageUseCase) :
    Factory<AllCatsViewModel> {

    override fun create(): AllCatsViewModel {
        return AllCatsViewModel(getImageUseCase)
    }
}