package com.example.roomproject.librosAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.R
import com.example.roomproject.model.LibrosDataClass

class RecyclerAdapter (val arrayList: ArrayList<LibrosDataClass>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context).inflate(R.layout.lista_libros,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val librs = arrayList[position]
        holder.render(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}