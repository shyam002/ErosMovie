package com.example.erosmovie.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

import com.example.erosmovie.R
import com.example.erosmovie.adapter.TopRatedAdapter
import com.example.erosmovie.database.MyDatabase
import com.example.erosmovie.model.Favourite
import com.example.erosmovie.model.Result

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavouriteFragment : Fragment() {
    lateinit var myDatabase: MyDatabase
    lateinit var favourite_recyclerview: RecyclerView
    var resultlist = ArrayList<Result>()
    lateinit var favlist: ArrayList<Favourite>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favourite, container, false)
        this.activity!!.title = "Favourite Movies"
        favourite_recyclerview = view.findViewById(R.id.favourite_recyclerview)
        // for right now in this test i am calling allowMainThreadQueries to allow database to call it in main thread.
        myDatabase = Room.databaseBuilder(this.activity!!, MyDatabase::class.java, "favouriteDb").allowMainThreadQueries().build()
        return view
    }

    override fun onResume() {
        setData()
        super.onResume()
    }

    fun setData(){
        favlist = myDatabase.myDao().allFavourite as ArrayList<Favourite>
        resultlist = ArrayList<Result>()
        for(item in favlist){
            val result = Result(item.adult,item.backdropPath,null,item.id,null,null,item.overview,null,item.posterPath,item.releaseDate,item.title,null,item.voteAverage,null,item.favourite)
            resultlist.add(result)
        }
        favourite_recyclerview.adapter =  TopRatedAdapter(resultlist, this.activity!!)
        favourite_recyclerview.layoutManager = GridLayoutManager(this.activity, 2)
    }
}
