package com.androidsquad.countriesapp

import com.androidsquad.countriesapp.model.Movie
import com.androidsquad.countriesapp.model.service.ApiService
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}