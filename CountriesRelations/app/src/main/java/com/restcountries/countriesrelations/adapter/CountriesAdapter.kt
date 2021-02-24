package com.restcountries.countriesrelations.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.restcountries.countriesrelations.R
import com.restcountries.countriesrelations.activities.CountryDetailsActivity
import com.restcountries.countriesrelations.data.CountryData
import com.restcountries.countriesrelations.data.CountryDetailsData
import com.restcountries.countriesrelations.viewholder.CountryViewHolder


class CountriesAdapter(private val context: Context, private var countriesList: List<CountryData>) :
    Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View? = layoutInflater.inflate(R.layout.countries_recycler_item, parent, false)
        return CountryViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val fullName = countriesList[position].name + " - " + countriesList[position].nativeName
        holder.lblCountryName.text = fullName
        holder.lblCountryName.setOnClickListener {

            val bordersListFullData = mutableListOf<CountryData>()
            val bordersListCodes = countriesList[position].borders
            // catching all countries data for next screen
            if (bordersListCodes != null) {
                for (borderCode in bordersListCodes) {
                    for (country in countriesList) {
                        if (borderCode == country.countryCode) {
                            bordersListFullData.add(country)
                        }
                    }
                }
            }
            val countryDetailsData = CountryDetailsData()
            countryDetailsData.bordersListData = bordersListFullData
            countryDetailsData.selectedCountry = countriesList[position]
            val intent = Intent(context, CountryDetailsActivity::class.java)
            intent.putExtra("country-data", countryDetailsData)
            context.startActivity(intent)
        }
    }

    fun initData(homeList: List<CountryData>) {
        this.countriesList = homeList
    }
}