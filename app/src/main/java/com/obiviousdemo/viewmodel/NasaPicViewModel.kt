package com.obiviousdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obiviousdemo.data.model.NasaPicModel
import com.obiviousdemo.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NasaPicViewModel(private val dataRepository: DataRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) { dataRepository.getJsonDataFromAsset() }
    }

    val nasaPicList: LiveData<NasaPicModel>
        get() = dataRepository.picsDataFromApi
}