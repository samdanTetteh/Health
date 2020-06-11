package com.ijikod.sensyne

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ijikod.sensyne.Model.Hospital

class HospitalViewModel(val context: Context) : ViewModel() {

    val hospitalRepository = Repository(context)
    val hospitalData = hospitalRepository.hospitalData
    val errorMsgs = hospitalRepository.errorMsg


}