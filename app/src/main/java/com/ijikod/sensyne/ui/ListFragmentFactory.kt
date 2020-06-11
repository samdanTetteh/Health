package com.ijikod.sensyne.ui

import androidx.fragment.app.FragmentFactory
import com.ijikod.sensyne.ui.fragments.DetailsFragment
import com.ijikod.sensyne.ui.fragments.ListFragment

class ListFragmentFactory(val inspector: Inspector) : FragmentFactory() {
    override fun instantiate(
        classLoader: ClassLoader,
        className: String
    ) = when (className) {
        ListFragment::class.java.name -> ListFragment(
            inspector
        )
        DetailsFragment::class.java.name -> DetailsFragment(
            inspector
        )
        else -> super.instantiate(classLoader, className)
    }
}