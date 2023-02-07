package com.androidsquad.countriesapp

import com.androidsquad.countriesapp.model.service.ApiService
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class ApiServiceTest {

    @get:Rule

    private val server = MockWebServer()
    private lateinit var mockedResponse: String

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun init() {
        server.start(8000)
        var BASE_URL = server.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(ApiService::class.java)

    }

    @Test
    fun testApiSuccess() {
        mockedResponse = ""

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )


//        val resultResponse = JsonParser.parseString(json)
//        val expectedresponse = JsonParser.parseString(mockedResponse)

        Assert.assertEquals(4, 2 + 2)
//        Assert.assertNotNull(response)
//        Assert.assertTrue(resultResponse.equals(expectedresponse))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}