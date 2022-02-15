package com.example.roomproject.librosAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.databinding.ListaLibrosBinding
import com.example.roomproject.model.LibrosDataClass

class ViewHolder(v:View) : RecyclerView.ViewHolder(v){
    private val binding = ListaLibrosBinding.bind(v)

    fun render(libro:LibrosDataClass){
        binding.titulo.text = libro.titulo
        binding.autor.text = libro.autor
    }
}