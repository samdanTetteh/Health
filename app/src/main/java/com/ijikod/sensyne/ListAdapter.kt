package com.ijikod.sensyne

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.sensyne.ListAdapter.viewHolder
import com.ijikod.sensyne.Model.Hospital
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(val hospitalList: List<Hospital> , val context: Context, val onListItemClick: onListItemClick) : RecyclerView.Adapter<viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view  = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return viewHolder(view)
    }


    override fun getItemCount() = hospitalList.size


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val hospital = hospitalList[position]
        with(holder){
            nameTxt.text = hospital.organisationName
            itemView.setOnClickListener {
                onListItemClick.onHospitalClick(hospital)
            }

        }
    }




    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTxt = itemView.findViewById<TextView>(R.id.name_txt)
    }

}


interface onListItemClick{
    fun onHospitalClick(data : Hospital)
}