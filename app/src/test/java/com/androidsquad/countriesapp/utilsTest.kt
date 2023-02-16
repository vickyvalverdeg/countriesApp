package com.androidsquad.countriesapp

import com.androidsquad.countriesapp.model.Continent
import com.androidsquad.countriesapp.model.Continents
import com.androidsquad.countriesapp.model.Country
import com.androidsquad.countriesapp.utils.Utils
import org.junit.Assert
import org.junit.Test

class utilsTest {
    @Test
    fun shouldReturnAnArrayOfCountriesString(){
        val country1 = Country ("country1","capital1", "currency1")
        val country2 = Country ("country2","capital2", "currency2")
        val country3 = Country ("country3","capital3", "currency3")
        val countriesArray = ArrayList<Country>()
        countriesArray.add(country1)
        countriesArray.add(country2)
        countriesArray.add(country3)
        val continent = Continent("Continente", countriesArray)
        val arrayContinents = ArrayList<Continent>()
        arrayContinents.add(continent)
        val continents = Continents(arrayContinents)
        val util = Utils()
        val expected = util.countriesListToArrayString(continent)
        Assert.assertArrayEquals(expected.toArray(), util.countriesListToArrayString(continent).toArray())
    }
}