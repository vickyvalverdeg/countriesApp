package com.androidsquad.countriesapp.utils

import com.androidsquad.countriesapp.model.Continent
import com.androidsquad.countriesapp.model.Continents

class Utils {
    fun countriesListToArrayString( continentName: Continent): ArrayList<String> {

        val continentSelected = continentName
        val arrayOfCountriesString = ArrayList<String>()
        for (i in continentSelected.countries.indices){
            arrayOfCountriesString.add(continentSelected.countries[i].name)
        }
        return arrayOfCountriesString
    }
}