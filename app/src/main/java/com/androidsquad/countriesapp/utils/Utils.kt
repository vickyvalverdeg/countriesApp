package com.androidsquad.countriesapp.utils

import com.androidsquad.countriesapp.model.Continent
import com.androidsquad.countriesapp.model.Continents

class Utils {
    fun countriesListToArrayString(continents: Continents, continentName: Continent): ArrayList<String> {
        val indexOfCountries = continents.continents.indexOf(continentName);
        val continentSelected = continents.continents[indexOfCountries]
        val arrayOfCountriesString = ArrayList<String>()
        for (i in continentSelected.countries.indices){
            arrayOfCountriesString.add(continentSelected.countries[i].toString())
        }
        return arrayOfCountriesString
    }
}