package com.example.roomproject.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomproject.R
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.librosAdapter.RecyclerAdapter
import com.example.roomproject.model.LibrosDataBase
import com.example.roomproject.model.LibrosDataClass
import com.example.roomproject.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
//    private val viewModel: MainViewModel = TODO()

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

        //val libro = LibrosDataClass(titulo = "La Odisea", autor = "Homero")

        database.libroDao.addLibro(LibrosDataClass(1, "La Odisea", "Homero"))
        //Cual de las dos es buena practica??
        //viewModel.libroDao.addLibro(libro)

        //var modelo: MainViewModel =
        //modelo.addLibro(libro = libro)
        val datos = database.libroDao.getLibros()
        //adapter.setData(datos)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
      /*  viewModel.libroDao.getLibros().observe(this) { list ->
            list.let {
                //TODO list o lista?
                adapter.setData(it)
                binding.recycler.adapter = adapter
            }
        }*/

        //lista = database.libroDao.getLibros()
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

    //menus
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_drop -> {
                borrar()
            }
            R.id.btn_consulta->{
                consulta()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun consulta() {
        startActivity(Intent(this, ConsultaActivity::class.java))
    }

    private fun borrar() {
        val database = Room.databaseBuilder(
            this, LibrosDataBase::class.java, "libros_database"
        )
            .allowMainThreadQueries()
            .build()

        database.libroDao.deleteAll()
    }


}