package com.androidsquad.countriesapp.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Continent(
    val name: String,
    val countries: List<Country>,
    val image: Int?,
    val code: String

)

data class Continents(
    @SerializedName("continents")
    val continents: List<Continent>
)
