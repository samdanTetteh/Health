package com.ijikod.sensyne.Application

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ijikod.sensyne.Data.Repository
import com.ijikod.sensyne.ui.ViewModelFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }



    companion object{
        lateinit var appContext : Context

        private fun provideRepository(context: Context): Repository {
            return Repository(context)

        }


        fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
            return ViewModelFactory(
                provideRepository(
                    context
                )
            )
        }
    }
}
