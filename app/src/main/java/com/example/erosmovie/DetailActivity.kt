package com.example.erosmovie

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.erosmovie.database.MyDatabase
import com.example.erosmovie.model.Favourite
import com.example.erosmovie.model.Result
import java.util.*

class DetailActivity : Activity() {
    lateinit var backimageview:ImageView
    lateinit var favimageview:CheckBox
    lateinit var titleTV: TextView
    lateinit var dateTV: TextView
    lateinit var descTV: TextView
    lateinit var myDatabase: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val result = intent.getParcelableExtra<Result>("movieResult")
        // for right now in this test i am calling allowMainThreadQueries to allow database to call it in main thread.
        myDatabase = Room.databaseBuilder(this, MyDatabase::class.java, "favouriteDb").allowMainThreadQueries().build()
        backimageview = findViewById(R.id.backimageview)
        favimageview = findViewById(R.id.favimageview)
        titleTV = findViewById(R.id.titleTV)
        dateTV = findViewById(R.id.dateTV)
        descTV = findViewById(R.id.descTV)

        titleTV.text = result.title
        dateTV.text = result.releaseDate
        descTV.text = result.overview
        favimageview.isChecked = myDatabase.myDao().getfavouriteStatus(result.id!!)
        val poster = "https://image.tmdb.org/t/p/w500/" + result.backdropPath
        Glide.with(this)
            .load(poster)
            .placeholder(R.drawable.backimage)
            .into(backimageview)

        favimageview.setOnCheckedChangeListener { buttonView, isChecked ->
                val msg = if (isChecked) "Movie added in favourite" else "Removed as favourite"
                Toast.makeText(this@DetailActivity,msg,Toast.LENGTH_SHORT).show()
            val favourite = Favourite()
            favourite.adult = result.adult
            favourite.id = result.id
            favourite.backdropPath = result.backdropPath
            favourite.favourite = isChecked
            favourite.overview = result.overview
            favourite.posterPath = result.posterPath
            favourite.releaseDate = result.releaseDate
            favourite.title = result.title
            favourite.voteAverage = result.voteAverage
            try {
                myDatabase.myDao().deleteByID(favourite.id!!)
            } catch (e: Exception) {
            }
            try {
                if(isChecked)
                myDatabase.myDao().addFavourite(favourite)
            } catch (e: Exception) {
            }
        }
    }
}
