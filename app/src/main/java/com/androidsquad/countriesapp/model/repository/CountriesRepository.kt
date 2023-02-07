package com.androidsquad.countriesapp.model.repository

import com.androidsquad.countriesapp.model.Country
import com.androidsquad.countriesapp.model.service.CountryService

/**
 * Repository that fetch countries list from countryService.
 */
interface CountryRepository {
    suspend fun getCountries(): List<Country>
}

class CountriesRepository(
    private val countryService: CountryService): CountryRepository{
    /** Fetches list of Country from countryService*/
    override suspend fun getCountries(): List<Country> = countryService.getCountries()
}
