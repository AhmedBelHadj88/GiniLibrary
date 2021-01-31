package com.tda.ginilibrary.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Image(

    @SerializedName("breeds")
    @Expose
    val breeds: List<Breed>?,
    @SerializedName("categories")
    @Expose
    val categories: List<Category>?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("width")
    @Expose
    val width: Int?,
    @SerializedName("height")
    @Expose
    val height: Int?,
    @SerializedName("pending")
    @Expose
    val pending: Int?,
    @SerializedName("approved")
    @Expose
    val approved: Int?,
    @SerializedName("rejected")
    @Expose
    val rejected: Int?,
    @SerializedName("sub_id")
    @Expose
    val sub_id: String?,
    @SerializedName("created_at")
    @Expose
    val created_at: String?,
    @SerializedName("original_filename")
    @Expose
    val original_filename: String?,
    @SerializedName("breed_ids")
    @Expose
    val breed_ids: String?
)