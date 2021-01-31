package com.tda.ginilibrary.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Weight (

    @SerializedName("imperial")
    @Expose
    val imperial : String?,
    @SerializedName("metric")
    @Expose
    val metric : String?
)