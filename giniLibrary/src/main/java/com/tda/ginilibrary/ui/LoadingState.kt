package com.tda.ginilibrary.ui

import com.tda.ginilibrary.data.model.Failure

internal sealed class LoadingState {
    object LOADING : LoadingState()
    object DONE : LoadingState()
    class ERROR(val failure: Failure) : LoadingState()
}