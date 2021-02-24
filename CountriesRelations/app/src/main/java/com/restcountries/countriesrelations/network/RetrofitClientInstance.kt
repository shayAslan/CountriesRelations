package com.restcountries.countriesrelations.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private lateinit var retrofit: Retrofit

    internal fun getRetrofitInstance(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
    }

}