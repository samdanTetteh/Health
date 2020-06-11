package com.ijikod.sensyne

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.ijikod.sensyne.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment(val inspector: Inspector) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  =   FragmentDetailsBinding.inflate(inflater,  container, false)
        val hospital = arguments?.let { DetailsFragmentArgs.fromBundle(it).hospital }


        hospital?.let {
            binding.hospitalInitials.text = it.getInitials()
            binding.hospitalNameTxt.text = it.organisationName
            binding.countryTxt.text = it.county
            binding.sectorTxt.text = it.sector
            binding.postcodeTxt.text = it.postcode
            binding.subTypeTxt.text = it.postcode

            it.organisationName?.let { it1 -> inspector.setTitle(it1) }
        }

        setHasOptionsMenu(true)

        inspector.setActionBarUpVisibility(true)

        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            inspector.getNavigation().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}
