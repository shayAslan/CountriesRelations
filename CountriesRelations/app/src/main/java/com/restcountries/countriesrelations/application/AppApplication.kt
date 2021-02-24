package com.restcountries.countriesrelations.application

import android.app.Application
import android.content.Context


class AppApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AppApplication
        fun getInstance(): AppApplication {
            return instance
        }
    }

    fun getContext(): Context {
        return applicationContext
    }


}