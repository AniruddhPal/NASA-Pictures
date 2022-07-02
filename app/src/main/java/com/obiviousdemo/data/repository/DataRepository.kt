package com.obiviousdemo.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.obiviousdemo.data.model.NasaPicModel
import com.obiviousdemo.utils.Helper

class DataRepository(private val context: Context) {

    private val tag = javaClass.simpleName
    private val picsLiveData = MutableLiveData<NasaPicModel>()

    val picsDataFromApi: LiveData<NasaPicModel>
        get() = picsLiveData


    fun getJsonDataFromAsset() {
        val jsonData: String? = Helper.getJsonFromAssets(context, "data.json")
        val dataClass: NasaPicModel =
            Helper.convertJSONtoPOJO(jsonData, NasaPicModel::class.java) as NasaPicModel
        picsLiveData.postValue(dataClass)
    }
}