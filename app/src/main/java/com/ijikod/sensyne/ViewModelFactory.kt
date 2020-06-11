package com.ijikod.sensyne

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HospitalViewModel(repository.context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}

