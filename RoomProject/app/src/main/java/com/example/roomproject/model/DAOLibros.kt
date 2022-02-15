package com.example.roomproject.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOLibros {
    @Query("select * from libros")
    fun getLibros():LiveData<List<LibrosDataClass>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLibro(libro:LibrosDataClass)

    @Delete
    fun deleteLibro(dltLibro: LibrosDataClass)

    @Query("update libros set titulo=:tituloNuevo, autor=:autorNuevo where id=:id")
    fun updateLibro(id: Int, tituloNuevo: String, autorNuevo: String)
}