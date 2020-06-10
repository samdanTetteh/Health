package com.ijikod.sensyne

import android.content.Context
import androidx.lifecycle.ViewModel

class ListViewModel(val context: Context) : ViewModel() {

    val hospitalRepository = Repository(context)
    val hospitalData = hospitalRepository.hospitalData


}