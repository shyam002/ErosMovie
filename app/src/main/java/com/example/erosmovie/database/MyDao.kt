package com.example.erosmovie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.erosmovie.model.Favourite
import com.example.erosmovie.model.Result

import java.util.ArrayList

@Dao
interface MyDao {


    @get:Query("select * from favouritetable")
    val allFavourite: List<Favourite>
    @Query("DELETE FROM favouritetable WHERE id = :id")
    fun deleteByID(id:Int):Int
    @Query("select favourite FROM favouritetable WHERE id = :id")
    fun getfavouriteStatus(id:Int):Boolean
    @Insert
    fun addFavourite(favourite: Favourite)

    @Delete
    fun deleteFavourite(favourite: Favourite)
}
