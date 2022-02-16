package com.example.roomproject.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DAOLibros {
    @Query("SELECT * FROM libros")
    fun getLibros():LiveData<List<LibrosDataClass>>

    @Query("SELECT * FROM Libros WHERE ID =:id")
    fun getId(id : Int):LiveData<List<LibrosDataClass>>

    @Query("SELECT * FROM Libros WHERE titulo =:tit")
    fun getTitulo(tit : String):LiveData<List<LibrosDataClass>>

    @Query("SELECT * FROM Libros WHERE autor =:au")
    fun getAutor(au : String):LiveData<List<LibrosDataClass>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLibro(libro:LibrosDataClass)

    @Delete
    fun deleteLibro(dltLibro: LibrosDataClass)

    @Query("UPDATE libros SET titulo=:tituloNuevo, autor=:autorNuevo WHERE id=:id")
    fun updateLibro(id: Int, tituloNuevo: String, autorNuevo: String)

    @Query("DELETE FROM Libros")
    fun deleteAll()
}