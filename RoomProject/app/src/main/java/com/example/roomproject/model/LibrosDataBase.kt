package com.example.roomproject.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LibrosDataClass::class), version = 1, exportSchema = false)
abstract class LibrosDataBase : RoomDatabase(){
    abstract val libroDao: DAOLibros
}