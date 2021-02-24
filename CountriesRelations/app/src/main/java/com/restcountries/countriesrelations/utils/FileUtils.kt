package com.restcountries.countriesrelations.utils

import com.google.gson.Gson
import com.restcountries.countriesrelations.application.AppApplication
import com.restcountries.countriesrelations.data.CountryData
import org.json.JSONArray
import java.io.*


object FileUtils {

    private const val COUNTRY_FILE_NAME = "/countries_file"

    fun saveCountriesToStorage(list: List<CountryData?>) {
        val json = Gson().toJson(list)
        val yourFilePath: String =
            AppApplication.getInstance().getContext().filesDir.toString() + COUNTRY_FILE_NAME
        val yourFile = File(yourFilePath)
        if (yourFile.exists())
            yourFile.delete()
        val fileOutputStream = FileOutputStream(yourFile)
        fileOutputStream.write(json.toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    fun loadCountriesFromStorage(): List<CountryData?>? {
        val text: String
        try {
            if (isCountryFileExists().not())
                return null

            val yourFilePath: String =
                AppApplication.getInstance().getContext().filesDir.toString() + COUNTRY_FILE_NAME
            val yourFile = File(yourFilePath)
            val inputStream: InputStream = FileInputStream(yourFile)
            val stringBuilder = StringBuilder()
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String?
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append(receiveString)
            }
            inputStream.close()
            text = stringBuilder.toString()
        } catch (e: Exception) {
            return null
        }
        val jsonArray = JSONArray(text)
        val list = arrayListOf<CountryData>()
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val countryData = CountryData()
            countryData.name = item.optString("name")
            countryData.area = item.optDouble("area")
            countryData.nativeName = item.optString("nativeName")
            countryData.countryCode = item.optString("alpha3Code")
            val bordersJsonArray = item.optJSONArray("borders")
            val bordersList = mutableListOf<String>()
            if (bordersJsonArray != null) {
                for (j in 0 until bordersJsonArray.length()) {
                    bordersList.add(bordersJsonArray.get(j).toString())
                }
                countryData.borders = bordersList
            }
            list.add(countryData)
        }
        return list
    }

    private fun isCountryFileExists(): Boolean {
        val yourFile =
            File(AppApplication.getInstance().getContext().filesDir.toString() + COUNTRY_FILE_NAME)
        return yourFile.exists()
    }


}