package com.tda.ginilibrary.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Category(

        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("name")
        @Expose
        val name: String
)