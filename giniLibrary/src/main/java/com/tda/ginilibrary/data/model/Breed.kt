package com.tda.ginilibrary.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Breed(

    @SerializedName("weight")
    @Expose
    val weight: Weight?,
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("vetstreet_url")
    @Expose
    val vetstreet_url: String?,
    @SerializedName("temperament")
    @Expose
    val temperament: String?,
    @SerializedName("origin")
    @Expose
    val origin: String?,
    @SerializedName("country_codes")
    @Expose
    val country_codes: String?,
    @SerializedName("country_code")
    @Expose
    val country_code: String?,
    @SerializedName("description")
    @Expose
    val description: String?,
    @SerializedName("life_span")
    @Expose
    val life_span: String?,
    @SerializedName("indoor")
    @Expose
    val indoor: Int?,
    @SerializedName("alt_names")
    @Expose
    val alt_names: String?,
    @SerializedName("adaptability")
    @Expose
    val adaptability: Int?,
    @SerializedName("affection_level")
    @Expose
    val affection_level: Int?,
    @SerializedName("child_friendly")
    @Expose
    val child_friendly: Int?,
    @SerializedName("dog_friendly")
    @Expose
    val dog_friendly: Int?,
    @SerializedName("energy_level")
    @Expose
    val energy_level: Int?,
    @SerializedName("grooming")
    @Expose
    val grooming: Int?,
    @SerializedName("health_issues")
    @Expose
    val health_issues: Int?,
    @SerializedName("intelligence")
    @Expose
    val intelligence: Int?,
    @SerializedName("shedding_level")
    @Expose
    val shedding_level: Int?,
    @SerializedName("social_needs")
    @Expose
    val social_needs: Int?,
    @SerializedName("stranger_friendly")
    @Expose
    val stranger_friendly: Int?,
    @SerializedName("vocalisation")
    @Expose
    val vocalisation: Int?,
    @SerializedName("experimental")
    @Expose
    val experimental: Int?,
    @SerializedName("hairless")
    @Expose
    val hairless: Int?,
    @SerializedName("natural")
    @Expose
    val natural: Int?,
    @SerializedName("rare")
    @Expose
    val rare: Int?,
    @SerializedName("rex")
    @Expose
    val rex: Int?,
    @SerializedName("suppressed_tail")
    @Expose
    val suppressed_tail: Int?,
    @SerializedName("short_legs")
    @Expose
    val short_legs: Int?,
    @SerializedName("wikipedia_url")
    @Expose
    val wikipedia_url: String?,
    @SerializedName("hypoallergenic")
    @Expose
    val hypoallergenic: Int?,
    @SerializedName("reference_image_id")
    @Expose
    val reference_image_id: String?
)