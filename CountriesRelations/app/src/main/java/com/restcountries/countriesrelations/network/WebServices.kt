package com.restcountries.countriesrelations.network

import com.restcountries.countriesrelations.definitions.Consts
import com.restcountries.countriesrelations.interfaces.CountryList
import com.restcountries.countriesrelations.data.CountryData
import retrofit2.Call
import retrofit2.Callback


object WebServices {

    fun getCountryList(objCallback: Callback<List<CountryData?>?>) {
        val service = RetrofitClientInstance.getRetrofitInstance(Consts.BASE_URL).create(CountryList::class.java)
        val call: Call<List<CountryData?>?> = service.countryList
        call.enqueue(objCallback)
    }

}