package com.androidsquad.countriesapp.model.api

import com.androidsquad.countriesapp.model.repository.CountryRepository

interface ApiCall{
    val countryRepository: CountryRepository
}

class CountryApi: ApiCall {
    private val BASE_URL = "https://restcountries.com/v3.1/all/"
    override val countryRepository: CountryRepository
        get() = TODO("Not yet implemented")
}