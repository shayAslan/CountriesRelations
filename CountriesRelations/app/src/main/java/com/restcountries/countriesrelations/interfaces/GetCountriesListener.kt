package com.restcountries.countriesrelations.interfaces

import com.restcountries.countriesrelations.data.CountryData

interface GetCountriesListener {

    fun onResponse(countryList: List<CountryData?>?)
    fun onError(text : String)


}