package com.tda.ginilibrary.data.model

import com.google.gson.annotations.SerializedName

internal data class ErrorBodyModel(
    @SerializedName("message") val message: String? = "",
    @SerializedName("status") val status: Int? = 0,
    @SerializedName("info") val error: String? = "",
)