package com.example.roomproject.ViewModel

import androidx.room.*
import com.example.roomproject.Model.Libros

@Dao
interface LibrosDao {

    @Query("SELECT * FROM Libros")
    fun all(): List <Libros>

    @Query("SELECT * FROM Libros WHERE id = :id")
    fun getId(id:Int): List<Libros>

    @Query("SELECT * FROM Libros WHERE titulo = :titulo")
    fun getTitulo(titulo:String): List<Libros>

    @Query("SELECT * FROM Libros WHERE autor = :autor")
    fun getAutor(autor:String): List<Libros>

    @Insert
    fun add(libros: Libros)

    @Insert
    fun addLibros(libros: List<Libros>)

    @Update
    fun modificar(libros: Libros)

    @Delete
    fun borrar()

    @Delete
    fun borrarLibro(libros: Libros)
}
