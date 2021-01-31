package com.tda.ginilibrary.data.mapper

import com.tda.ginilibrary.data.model.Image

internal class ImageToCatImageMapper : Mapper<List<Image>, List<CatImage>> {
    override fun map(from: List<Image>?): List<CatImage> {
        return from?.map {
            CatImage(it.url.orEmpty())
        } ?: arrayListOf()
    }
}