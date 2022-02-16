package com.example.roomproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Libros")
data class LibrosDataClass(
    @PrimaryKey(autoGenerate = true)
    var ID: Int? = 0,
    @ColumnInfo(name = "titulo")
    var titulo : String,
    @ColumnInfo(name = "autor")
    var autor: String)
