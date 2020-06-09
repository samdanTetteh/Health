package com.ijikod.sensyne

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    lateinit var viewModel: ListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_list, container, false)

        viewModel = ViewModelProviders.of(this, App.provideViewModelFactory(requireContext())).get(ListViewModel::class.java)


        return view
    }

}
