package com.example.quotesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanse {
    private const val BASE_URL = "https://cat-fact.herokuapp.com"

    val api: ApiRequests by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)
    }
}