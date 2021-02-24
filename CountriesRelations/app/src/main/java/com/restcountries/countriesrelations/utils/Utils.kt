package com.restcountries.countriesrelations.utils

import com.restcountries.countriesrelations.data.CountryData

object Utils {

    fun sortAZ(list: List<CountryData>): List<CountryData> {
        return list.sortedBy { it.name }
    }

    fun sortZA(list: List<CountryData>): List<CountryData> {
        return list.reversed()
    }

    fun sortSize(list: List<CountryData>): List<CountryData> {
        return list.sortedBy { it.area }.reversed()
    }
}