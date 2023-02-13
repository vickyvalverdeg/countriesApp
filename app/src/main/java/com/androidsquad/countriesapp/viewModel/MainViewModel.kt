package com.androidsquad.countriesapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidsquad.countriesapp.model.Continents
import com.androidsquad.countriesapp.model.service.ApiService
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var continentListResponse:List<Continents> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getCountryList() {
        val paramObject = JsonObject()
        paramObject.addProperty("query", "{ continents { code name countries { code name native phone capital currency emoji emojiU } } }")

        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val continentList = apiService.getCountries(paramObject)
                continentListResponse = listOf(continentList)
            }
            catch (e: Exception) {
                Log.i("Error response", e.message.toString())
                errorMessage = e.message.toString()
            }
        }
    }
}

