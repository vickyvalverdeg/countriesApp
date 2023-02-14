package com.androidsquad.countriesapp.model

import com.google.gson.annotations.SerializedName

data class Continent(
    val name: String,
    val countries: List<Country>

)

data class Continents(
    @SerializedName("continents")
    val continents: List<Continent>
)
