package com.example.roomproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.roomproject.databinding.ActivityInsertarLibroBinding
import com.example.roomproject.model.LibrosDataBase
import com.example.roomproject.model.LibrosDataClass

lateinit var binding: ActivityInsertarLibroBinding

class InsertarLibro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInsertarLibroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listeners()
    }

    private fun listeners() {
        binding.btnGuardar.setOnClickListener {
            guardar()
        }
        binding.btnVolver.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun guardar() {
        val titulo = binding.etTitulo.text.toString()
        val autor = binding.etAutor.text.toString()

        val database = Room.databaseBuilder(
            this, LibrosDataBase::class.java, "libros_database"
        )
            .allowMainThreadQueries()
            .build()

        if (!titulo.equals("") && !autor.equals("")) {
            database.libroDao.addLibro(LibrosDataClass(null, titulo, autor))
        }
    }
}