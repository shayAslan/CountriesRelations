package com.restcountries.countriesrelations.repo

import com.restcountries.countriesrelations.interfaces.GetCountriesListener
import com.restcountries.countriesrelations.network.WebServices
import com.restcountries.countriesrelations.data.CountryData
import com.restcountries.countriesrelations.utils.FileUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object AppRepository  {

    private var mCountriesList: List<CountryData?>? = null

    //get data from file system. if not, get from WS and save in file system for next time
    fun getAllCountries(listener: GetCountriesListener) {
        mCountriesList = FileUtils.loadCountriesFromStorage()
        if (mCountriesList != null && mCountriesList!!.isNotEmpty()) {
            listener.onResponse(mCountriesList)
        } else {
            WebServices.getCountryList(object : Callback<List<CountryData?>?> {
                override fun onResponse(call: Call<List<CountryData?>?>, response: Response<List<CountryData?>?>) {
                    mCountriesList = response.body()
                    if (mCountriesList != null) {
                        FileUtils.saveCountriesToStorage(mCountriesList!!)
                        listener.onResponse(mCountriesList)
                    } else {
                        listener.onError("Error: List is empty")
                    }
                }

                override fun onFailure(call: Call<List<CountryData?>?>, t: Throwable) {
                    listener.onError(t.message.toString())
                }
            })
        }
    }

}