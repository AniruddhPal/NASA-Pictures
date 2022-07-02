package com.obiviousdemo

import android.app.Application
import com.obiviousdemo.data.repository.DataRepository


class MyApplicationClass : Application() {

    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()
        intialize()
    }

    private fun intialize() {
        try {
            dataRepository = DataRepository(applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}