package com.tda.ginilibrary.data.mapper

internal interface Mapper<F, T> {
    fun map(from: F?): T
}