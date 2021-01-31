package com.tda.ginilibrary

import android.graphics.Bitmap

interface CatImageListener {

    fun setImageUrl(url: String)
    fun getImageBitmap(bitmap: Bitmap)
}