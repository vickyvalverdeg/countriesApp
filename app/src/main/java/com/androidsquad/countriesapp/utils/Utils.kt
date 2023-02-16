package com.androidsquad.countriesapp.utils

import com.androidsquad.countriesapp.model.Continent

class Utils {
    fun countriesListToArrayString( continentName: Continent): ArrayList<String> {

        val continentSelected = continentName
        val arrayOfCountriesString = ArrayList<String>()
        for (i in continentSelected.countries.indices){
            arrayOfCountriesString.add(continentSelected.countries[i].toString())
        }
        return arrayOfCountriesString
    }

    fun getNameCountryByJson(countrySelected: String): String{
        var name = ""
        for (i in countrySelected.indices){
            val currentCharacter = countrySelected[i]
            if (i >= 13){
                if (currentCharacter != ','){
                    name = name+currentCharacter
                }else if (currentCharacter == ','){
                    break
                }
            }
        }
        return name
    }
    fun getCapitalByJson(countrySelected: String): String{
        var capital = ""
        var commaPosicion = 0
        var startCapital = commaPosicion+8
        var commaCounter = 0
        for (i in countrySelected.indices){
            val currentCharacter = countrySelected[i]
            if (currentCharacter==','){
                if(commaCounter>0){
                    break
                }
                commaCounter++
                commaPosicion = i
                startCapital = commaPosicion+10
            }
            if (commaPosicion>0 && i>=startCapital){
                if (currentCharacter != ','){
                    capital = capital+currentCharacter
                }
            }
        }
        return capital
    }

    fun getCurrencyByJson(countrySelected: String): String{
        var currency = ""
        var commaPosicion = 0
        var startCurrency = 0
        var commaCounter = 0
        for (i in countrySelected.indices){
            val currentCharacter = countrySelected[i]
            if (currentCharacter==','){
                commaCounter++
                if (commaCounter>1){
                    commaPosicion = i
                    startCurrency = commaPosicion+11
                }
            }

            if (commaPosicion>0 && i>=startCurrency){
                if (currentCharacter != ')'){
                    currency = currency+currentCharacter
                }
            }
        }
        return currency
    }
}