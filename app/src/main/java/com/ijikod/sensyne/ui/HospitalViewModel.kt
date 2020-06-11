package com.ijikod.sensyne

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ijikod.sensyne.Data.Repository

class HospitalViewModel(val context: Context) : ViewModel() {

    val hospitalRepository = Repository(context)
    val hospitalData = hospitalRepository.hospitalData
    val errorMsgs = hospitalRepository.errorMsg


    fun getData(isNhs : Boolean ){
        hospitalRepository.getHospitalData(isNhs)
    }
}