package com.androidsquad.countriesapp.model.service

import com.androidsquad.countriesapp.model.Country
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun getCountries(@Body body: JsonObject) : List<Country>

    companion object {
        private var apiService: ApiService? = null
        private var gson = GsonBuilder()
            .setLenient()
            .create()

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private var client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()


        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://countries.trevorblades.com")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }

}