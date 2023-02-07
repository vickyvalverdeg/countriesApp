package com.androidsquad.countriesapp.model.service

import com.androidsquad.countriesapp.model.Country

interface CountryService {
//    @GET("countries")
    suspend fun getCountries(): List<Country>
}