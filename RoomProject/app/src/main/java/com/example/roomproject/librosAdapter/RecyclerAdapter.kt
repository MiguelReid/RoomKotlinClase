package com.example.roomproject.librosAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.R
import com.example.roomproject.model.LibrosDataClass

class RecyclerAdapter (val lista: MutableList<LibrosDataClass>): RecyclerView.Adapter<ViewHolder>() {
    //lo he cambiado lista a MutableList para q en el mainactivity lo pueda meter en el adapter
    private var oldData = emptyList<LibrosDataClass>()//datos viejo

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.lista_libros,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val libros = lista[position]
        holder.render(lista.get(position))
        //yo lo hago siempre diferente sin el render y en la misma clase
        //si funciona dejarlo si pruebo la otra
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    //para eliminar al deslizar
    fun removeAt(position: Int){
        lista.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setData(dato: List<LibrosDataClass>){
        oldData=dato
        notifyDataSetChanged()
    }
}