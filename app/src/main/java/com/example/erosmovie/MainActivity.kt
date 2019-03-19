package com.example.erosmovie

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.erosmovie.fragments.FavouriteFragment
import com.example.erosmovie.fragments.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fManager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_toprated -> {
                setTopRatedFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourite-> {
                setFavouriteFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState != null) {
            return
        }
        setTopRatedFragment()

    }
    fun setTopRatedFragment(){
        val transaction = fManager.beginTransaction()
        val fragment = TopRatedFragment()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
    fun setFavouriteFragment(){
        val transaction = fManager.beginTransaction()
        val fragment = FavouriteFragment()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }
}
