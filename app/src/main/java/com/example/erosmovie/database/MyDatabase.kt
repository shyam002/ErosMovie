package com.example.erosmovie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.erosmovie.model.Favourite
import com.example.erosmovie.model.Result

@Database(entities = [Favourite::class], version = 1,exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao
}
