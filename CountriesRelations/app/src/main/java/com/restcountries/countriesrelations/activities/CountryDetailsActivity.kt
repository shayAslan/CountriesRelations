package com.restcountries.countriesrelations.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.restcountries.countriesrelations.R
import com.restcountries.countriesrelations.data.CountryDetailsData
import com.restcountries.countriesrelations.databinding.ActivityCountryDetailsBinding
import java.lang.StringBuilder

class CountryDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.right_to_middle_anim, R.anim.middle_to_left_anim)
        binding = ActivityCountryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDataWithView()
    }

    private fun initDataWithView() {
        val countryDetailsData = intent.getSerializableExtra("country-data") as CountryDetailsData
        if (intent != null && countryDetailsData != null) {
            binding.lblCountryTitle.text =
                "${getString(R.string.country_title_prefix)}${countryDetailsData.selectedCountry.name}"
            val stringBuilder = StringBuilder()
            for (country in countryDetailsData.bordersListData) {
                stringBuilder.append(country.name + " - " + country.nativeName)
                stringBuilder.appendLine()
            }
            if (stringBuilder.isEmpty())
                stringBuilder.append(getString(R.string.non))
            binding.lblCountryBordersWith.text = stringBuilder.toString()

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_middle_anim, R.anim.middle_to_right_anim)
    }
}