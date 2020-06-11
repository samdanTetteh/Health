package com.ijikod.sensyne.Data

import androidx.room.Dao
import androidx.room.Insert
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

    @Insert
     fun insertHospitalsTest(hospitals: List<Hospital>)

    @Query("DELETE from Hospitals")
    suspend fun deleteAll()

}