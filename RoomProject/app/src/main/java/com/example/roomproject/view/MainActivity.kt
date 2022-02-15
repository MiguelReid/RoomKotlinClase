package com.example.roomproject.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.librosAdapter.RecyclerAdapter
import com.example.roomproject.model.LibrosDataBase
import com.example.roomproject.model.LibrosDataClass
import com.example.roomproject.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button

    //No estoy seguro de si se puede reutilizar la dataclass
    lateinit var lista: MutableList<LibrosDataClass>
    lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lista = mutableListOf()
        rellenarLayout()
        swipe()
    }

    private fun rellenarLayout() {
        val database = Room.databaseBuilder(
            this, LibrosDataBase::class.java, "libros_database"
        )
            .allowMainThreadQueries()
            .build()

        database.libroDao.addLibro(Libros(titulo = "La Odisea", autor = "Homero"))
        val inventario = database.libroDao.getLibros()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
        adapter = RecyclerAdapter(inventario)
        binding.recycler.adapter = adapter
    }

    private fun swipe() {
        val itemTouch = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder

            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.recycler.adapter as RecyclerAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val ith = ItemTouchHelper(itemTouch)
        ith.attachToRecyclerView(binding.recycler)
    }
}