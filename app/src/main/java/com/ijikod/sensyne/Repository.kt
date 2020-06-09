package com.ijikod.sensyne

import android.content.Context
import android.net.ConnectivityManager
import com.ijikod.sensyne.App.Companion.appContext

class Repository {










    //Todo: how can i do this better
    @Suppress("DEPRECATION")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}