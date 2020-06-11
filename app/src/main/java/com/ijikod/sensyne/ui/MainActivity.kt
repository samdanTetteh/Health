package com.ijikod.sensyne.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ijikod.sensyne.R

class MainActivity : AppCompatActivity(), Inspector {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory =
            ListFragmentFactory(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setActionBarUpVisibility(enabled: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun getNavigation(): NavController {
        return  Navigation.findNavController(this,
            R.id.nav_host
        )
    }

}


interface Inspector {

    fun setActionBarUpVisibility(enabled : Boolean)

    fun setTitle(title : String)

    fun getNavigation(): NavController
}
