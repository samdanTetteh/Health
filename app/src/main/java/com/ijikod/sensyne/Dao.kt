package com.ijikod.sensyne

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ijikod.sensyne.Model.Hospital

@Dao
interface Dao {

    @Query("SELECT * from Hospitals")
    fun getAll(): List<Hospital>

    @Insert
    suspend fun insertHospital(hospital: Hospital)

    @Insert
    suspend fun insertHospitals(hospitals: List<Hospital>)

    @Query("DELETE from Hospitals")
    suspend fun deleteAll()

}