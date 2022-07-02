package com.obiviousdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.obiviousdemo.data.repository.DataRepository

class NasaPicViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NasaPicViewModel(dataRepository) as T
    }
}