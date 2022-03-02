package com.example.getdatafromfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val emplist : ArrayList<emp>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_item,
            parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = emplist[position]

        holder.empNo.text = currentitem.empNo
        holder.empName.text = currentitem.empName
        holder.phoneNo.text = currentitem.phoneNo


    }

    override fun getItemCount(): Int {
        return emplist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val empNo : TextView = itemView.findViewById(R.id.tvEmpNo)
        val empName : TextView = itemView.findViewById(R.id.tvEmpName)
        val phoneNo : TextView = itemView.findViewById(R.id.tvPhoneNo)



    }
}