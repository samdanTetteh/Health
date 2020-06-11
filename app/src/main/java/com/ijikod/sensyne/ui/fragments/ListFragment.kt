package com.ijikod.sensyne.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.sensyne.*
import com.ijikod.sensyne.Application.App
import com.ijikod.sensyne.Model.Hospital
import com.ijikod.sensyne.ui.HospitalViewModel
import com.ijikod.sensyne.ui.Inspector
import com.ijikod.sensyne.ui.ListAdapter
import com.ijikod.sensyne.ui.onListItemClick

/**
 * A simple [Fragment] subclass.
 */
class ListFragment(val inspector: Inspector) : Fragment() {

    lateinit var viewModel: HospitalViewModel
    lateinit var adapter: ListAdapter
    lateinit var hospitalList : RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var errorTxt: TextView

    lateinit var listener : onListItemClick


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this,
            App.provideViewModelFactory(
                requireContext()
            )
        ).get(HospitalViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_list, container, false)
        hospitalList = view.findViewById(R.id.hospital_list)
        progressBar = view.findViewById(R.id.progressBar)
        errorTxt = view.findViewById(R.id.error_txt)


        listener = object : onListItemClick {
            override fun onHospitalClick(data: Hospital) {
                val action =
                    ListFragmentDirections.actionListFragmentToDetailsFragment(
                        data
                    )
                inspector.getNavigation().navigate(action)
            }
        }

        viewModel.hospitalData.observe(requireActivity(), Observer {
            Log.d("in list fragment",  "size of data = ${it.size}")
            showList(it.isNotEmpty())
            adapter =
                ListAdapter(it, requireContext(), listener)
            hospitalList.adapter = adapter
        })

        viewModel.errorMsgs.observe(requireActivity(), Observer {
            errorTxt.text = it
            showList(false)
        })

        setHasOptionsMenu(true)

        inspector.setTitle(getString(R.string.home_title))
        inspector.setActionBarUpVisibility(false)

        return view
    }


    private fun showList(showList: Boolean){
        if (showList){
            progressBar.visibility = View.GONE
            errorTxt.visibility = View.GONE
            hospitalList.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
            errorTxt.visibility = View.VISIBLE
            hospitalList.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId.equals(R.id.all)){
            viewModel.getData(false)
        }else{
            viewModel.getData(true)
        }


        return super.onOptionsItemSelected(item)
    }



}
