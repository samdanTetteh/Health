package com.ijikod.sensyne.Data

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.ijikod.sensyne.Service.ApiClient
import com.ijikod.sensyne.Application.App.Companion.appContext
import com.ijikod.sensyne.FileHelper
import com.ijikod.sensyne.FileHelper.Companion.createFile
import com.ijikod.sensyne.Model.Hospital
import com.ijikod.sensyne.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.IOException

class Repository(val context: Context) {

    lateinit var fileReader: BufferedReader

    val hospitalData = MutableLiveData<List<Hospital>>()
    val errorMsg = MutableLiveData<String>()
    private val dao = SensyneDatabase.getDatabase(context).hospitalDao()


    init {
        CoroutineScope(Dispatchers.IO).launch{
            if (dao.getAll().isEmpty()){
                downloadFile()
            }else{
                hospitalData.postValue(dao.getAll())
            }
        }

    }
    
    
    
    fun getHospitalData(isFilter: Boolean){
        CoroutineScope(Dispatchers.IO).launch {

            hospitalData.postValue(emptyList())
            if (isFilter){
                hospitalData.postValue(dao.getNHSHospitals(context.getString(R.string.nhs_filter)))
            }else{
                hospitalData.postValue(dao.getAll())
            }
        }

    }





    @WorkerThread
    suspend fun downloadFile() {
        if (isNetworkAvailable()){
            try {
                val file = withContext(Dispatchers.IO) {
                    val response = ApiClient()
                        .getService().downloadFile(context.getString(R.string.file_name)).execute()
                    val buffer = response.body()?.byteStream()
                    var file: File? = null
                    if (buffer != null) {
                        file = context?.let { createFile(it, appContext.getString(R.string.file), context.getString(
                            R.string.file_extension
                        )) }
                        if (file != null) {
                            FileHelper.copyStreamToFile(
                                buffer,
                                file
                            )
                        }
                    }
                    file
                }
                
                
                    if (file == null) {
                        // show error.
                        errorMsg.postValue(context.getString(R.string.error_msg))
                    } else {
                        Log.d("downloaded file path", file.absolutePath)
                        readDataFromCache()

                    }
                
            } catch (e: Exception) {
                errorMsg.postValue(e.message)
            }

        }else{
            errorMsg.postValue(context.getString(R.string.error_txt))
            
        }



    }



    private suspend fun readDataFromCache() {
        val hospitals = mutableListOf<Hospital>()

 try {
     
 

        var line: String?
     fileReader = FileHelper.readFile(context)
     fileReader?.readLine()

        // Read the file line by line starting from the second line

        line = fileReader?.readLine()
        while (line != null) {
            val lineData = line.split(Regex("""[�]"""))
            if (lineData.isNotEmpty()) {
                val hospital  = Hospital(
                    organisationID = lineData[0].toLong(),
                    organisationCode = lineData[1],
                    organisationType = lineData[2],
                    subType = lineData[3],
                    sector =lineData[4],
                    organisationStatus = lineData[5],
                    isPimsManaged = lineData[6],
                    organisationName = lineData[7],
                    address1 = lineData[8],
                    address2 = lineData[9],
                    address3 = lineData[10],
                    city = lineData[11],
                    county = lineData[12],
                    postcode = lineData[13],
                    latitude = lineData[14].toString(),
                    longitude = lineData[15].toString(),
                    parentODSCode = lineData[16],
                    parentName = lineData[17],
                    phone = lineData[18],
                    email = lineData[19],
                    website = lineData[20],
                    fax = lineData[21]
                )

                hospitals.add(hospital)
            }
            line = fileReader.readLine()
        }

     Log.d("hospital>>>>>>>>", "${hospitals.size} is the size")
     dao.deleteAll()
     dao.insertHospitals(hospitals)
     hospitalData.postValue(dao.getAll())

 }catch (e: Exception){
     Log.e("error reading file", e.toString(), e)
//     errorMsg.postValue(context.getString(R.string.error_reading_file_txt))
     errorMsg.postValue(e.toString())
 }finally {
     try {
         fileReader.close()
     }catch (e: IOException){
         errorMsg.postValue(context.getString(R.string.error_closing_file_txt))
         CoroutineScope(Dispatchers.Main).launch {
             Toast.makeText(context, context.getString(R.string.error_closing_file_txt), Toast.LENGTH_LONG).show()
         }
     }
 }

    }








    //Todo: how can i do this better
    @Suppress("DEPRECATION")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}