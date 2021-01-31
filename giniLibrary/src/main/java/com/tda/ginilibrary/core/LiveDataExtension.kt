package com.tda.ginilibrary.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

internal fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

internal inline fun <reified T : ViewModel> Fragment.getViewModel(
    factory: Factory<T>,
    body: T.() -> Unit
): T {
    val vm = factory.create()
    vm.body()
    return vm
}