package com.example.roomproject.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Libros(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titulo: String,
    val editorial: String,
    val autor: String,
    val nejemplares: Int,
    val anioedicion: Int
)
