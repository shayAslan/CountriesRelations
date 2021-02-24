package com.restcountries.countriesrelations.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountryData : Serializable {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("area")
    @Expose
    var area: Double? = null

    @SerializedName("borders")
    @Expose
    var borders: MutableList<String>? = null

    @SerializedName("nativeName")
    @Expose
    var nativeName: String? = null

    @SerializedName("alpha3Code")
    @Expose
    var countryCode: String? = null


}