package com.example.roomproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomproject.model.DAOLibros
import com.example.roomproject.model.LibrosDataClass

class MainViewModel(val libroDao:DAOLibros): ViewModel() {

    fun todosLibros():LiveData<List<LibrosDataClass>> = libroDao.getLibros()

    fun addLibro(libro: LibrosDataClass){
        libroDao.addLibro(libro)
    }

    fun removeLibro(libro: LibrosDataClass){
        libroDao.deleteLibro(libro)
    }

    fun updateLibro(id: Int, tituloNuevo:String, autorNuevo:String){
        libroDao.updateLibro(id,tituloNuevo,autorNuevo)
    }

    fun borrarTodo(){
        libroDao.deleteAll()
    }

    fun consultarId(id: Int):LiveData<List<LibrosDataClass>> = libroDao.getId(id)

    fun consultarTitulo(tit: String):LiveData<List<LibrosDataClass>> = libroDao.getTitulo(tit)

    fun consultarAutor(au: String):LiveData<List<LibrosDataClass>> = libroDao.getAutor(au)
}