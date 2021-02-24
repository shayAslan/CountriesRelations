package com.restcountries.countriesrelations.data

import java.io.Serializable

class CountryDetailsData : Serializable {

    lateinit var selectedCountry: CountryData
    lateinit var bordersListData: List<CountryData>

}