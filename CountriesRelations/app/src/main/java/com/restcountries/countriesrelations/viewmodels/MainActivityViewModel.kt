package com.restcountries.countriesrelations.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.restcountries.countriesrelations.interfaces.GetCountriesListener
import com.restcountries.countriesrelations.data.CountryData
import com.restcountries.countriesrelations.repo.AppRepository



class MainActivityViewModel : ViewModel() {

    val countryListLiveData: MutableLiveData<List<CountryData?>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getCountriesList() {
        isLoading.postValue(true)
        AppRepository.getAllCountries(object : GetCountriesListener {
            override fun onResponse(countryList: List<CountryData?>?) {
                isLoading.postValue(false)
                countryListLiveData.postValue(countryList)
            }

            override fun onError(text: String) {
                countryListLiveData.postValue(listOf())
                isLoading.postValue(false)
            }
        })

    }

}