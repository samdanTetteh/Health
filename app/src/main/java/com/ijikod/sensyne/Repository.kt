package com.ijikod.sensyne

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.ijikod.sensyne.App.Companion.appContext
import com.ijikod.sensyne.FileHelper.Companion.createFile
import com.ijikod.sensyne.Model.Hospital
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class Repository(val context: Context) {


    val hospitalData = MutableLiveData<Hospital>()
    val errorMsg = MutableLiveData<String>()


    init {

        CoroutineScope(Dispatchers.IO).launch{
            downloadFile()
        }

    }





    @WorkerThread
    suspend fun downloadFile() {
        if (isNetworkAvailable()){
            try {
                val file = withContext(Dispatchers.IO) {
                    val response = ApiClient().getService().downloadFile(context.getString(R.string.file_name)).execute()
                    val buffer = response.body()?.byteStream()
                    var file: File? = null
                    if (buffer != null) {
                        file = context?.let { createFile(it, appContext.getString(R.string.file), context.getString(R.string.file_extension)) }
                        if (file != null) {
                            FileHelper.copyStreamToFile(buffer, file)
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




    private fun readDataFromCache() {
        val json = FileHelper.readFile(context)
        Log.d("file from file system", json)
    }








    //Todo: how can i do this better
    @Suppress("DEPRECATION")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}