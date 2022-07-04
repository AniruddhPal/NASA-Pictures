package com.obiviousdemo.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.obiviousdemo.data.model.NasaPicModel
import com.obiviousdemo.utils.Helper

class DataRepository {

    private val picsLiveData = MutableLiveData<NasaPicModel>()

    val picsDataFromApi: LiveData<NasaPicModel>
        get() = picsLiveData


    fun getJsonDataFromAsset(json: String?) {
        val dataClass: NasaPicModel =
            Helper.convertJSONtoPOJO(json, NasaPicModel::class.java) as NasaPicModel
        picsLiveData.postValue(dataClass)
    }
}