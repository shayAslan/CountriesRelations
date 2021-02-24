package com.restcountries.countriesrelations.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.restcountries.countriesrelations.R

class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val lblCountryName: TextView = itemView.findViewById(R.id.lblCountryName)

}