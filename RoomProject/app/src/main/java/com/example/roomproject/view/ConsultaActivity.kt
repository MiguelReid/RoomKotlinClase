package com.example.roomproject.view

import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomproject.databinding.ActivityConsultaBinding
import com.example.roomproject.librosAdapter.RecyclerAdapter
import com.example.roomproject.model.LibrosDataBase
import com.example.roomproject.model.LibrosDataClass
import com.example.roomproject.viewModel.MainViewModel

class ConsultaActivity : AppCompatActivity() {
    lateinit var binding: ActivityConsultaBinding

    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button

    //No estoy seguro de si se puede reutilizar la dataclass
    lateinit var lista: MutableList<LibrosDataClass>
    lateinit var adapter: RecyclerAdapter

    var termino = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Room.databaseBuilder(
            this, LibrosDataBase::class.java, "libros_database"
        )
            .allowMainThreadQueries()
            .build()



        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        /*
        viewModel.libroDao.getLibros().observe(this) { list ->
            list.let {
                adapter.setData(it)
                binding.recyclerView.adapter = adapter
            }
        }
        Queremos enseñar todos los datos en el otro activity?
        */


        setUp()
    }

    private fun setUp() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) {
                    termino = p0
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
        binding.rgConsulta.setOnCheckedChangeListener { _, i ->
            when (i) {
                binding.rdId.id -> {
                    val num = termino.toInt()
                    buscaId(num)
                }
                binding.rdAutor.id -> {
                    buscaAutor(termino)
                }
                binding.rdTitulo.id -> {
                    buscarTitulo(termino)
                }
            }
        }
    }

    private fun buscarTitulo(c: String) {
        val buscar = "%$c%"

        viewModel.libroDao.getTitulo(c).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }

    }

    private fun buscaAutor(c: String) {
        val buscar = "%$c%"

        viewModel.libroDao.getAutor(c).observe(this, { list ->
            list.let {
                adapter.setData(it)
            }
        })
    }

    private fun buscaId(num: Int) {
        val buscar = "%$num%"

        viewModel.libroDao.getId(num).observe(this, { list ->
            list.let {
                adapter.setData(it)
            }
        }

        )

    }
}