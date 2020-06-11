package com.ijikod.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ijikod.sensyne.Model.Hospital

@Dao
interface Dao {

    @Query("SELECT * from Hospitals Order by organisationName ASC")
    fun getAll(): List<Hospital>


    @Query("SELECT * from Hospitals where (sector Like :q)  Order by organisationName ASC")
    fun getNHSHospitals(q : String): List<Hospital>

    @Insert
    suspend fun insertHospital(hospital: Hospital)

    @Insert
    suspend fun insertHospitals(hospitals: List<Hospital>)

    @Query("DELETE from Hospitals")
    suspend fun deleteAll()

}