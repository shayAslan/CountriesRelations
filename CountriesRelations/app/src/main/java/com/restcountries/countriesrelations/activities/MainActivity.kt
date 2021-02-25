package com.restcountries.countriesrelations.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.restcountries.countriesrelations.R
import com.restcountries.countriesrelations.adapter.CountriesAdapter
import com.restcountries.countriesrelations.data.CountryData
import com.restcountries.countriesrelations.databinding.ActivityMainBinding
import com.restcountries.countriesrelations.utils.Utils
import com.restcountries.countriesrelations.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var mCountriesList: List<CountryData> = listOf()
    private lateinit var mCountriesAdapter: CountriesAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModal()
        initRadioGroup()
        mMainActivityViewModel.getCountriesList()
    }

    private fun initRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener(this)
    }

    private fun initViewModal() {
        mMainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mMainActivityViewModel.isLoading.observe(this, { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.radioGroup.visibility = View.INVISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.radioGroup.visibility = View.VISIBLE
            }

        })
        mMainActivityViewModel.countryListLiveData.observe(this, { countries ->
            mCountriesList = countries as List<CountryData>
            if (mCountriesList.isEmpty()) {
                binding.refreshBtn.visibility = View.VISIBLE
            } else {
                initRecycler(mCountriesList)
                binding.refreshBtn.visibility = View.GONE
            }
        })
    }

    private fun initRecycler(countriesList: List<CountryData>) {
        binding.countriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCountriesAdapter = CountriesAdapter(this, countriesList)
        binding.countriesRecyclerView.adapter = mCountriesAdapter
    }

    override fun onClick(v: View) {
        if (v.id == R.id.refreshBtn) {
            mMainActivityViewModel.getCountriesList()
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.radioAZ -> {
                mCountriesList = Utils.sortAZ(mCountriesList)
            }
            R.id.radioZA -> {
                mCountriesList = Utils.sortZA(mCountriesList)
            }
            R.id.radioSize -> {
                mCountriesList = Utils.sortSize(mCountriesList)
            }
        }
        mCountriesAdapter.initData(mCountriesList)
        mCountriesAdapter.notifyDataSetChanged()
        binding.countriesRecyclerView.layoutManager!!.scrollToPosition(0)
    }

}