package com.restcountries.countriesrelations.interfaces

import com.restcountries.countriesrelations.data.CountryData
import retrofit2.Call
import retrofit2.http.GET

interface CountryList {

    @get:GET("all")
    val countryList: Call<List<CountryData?>?>
}

