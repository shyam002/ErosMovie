package com.example.erosmovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.erosmovie.DetailActivity
import com.example.erosmovie.R
import kotlinx.android.synthetic.main.top_rated_movie_item.view.*

class TopRatedAdapter(val items: ArrayList<com.example.erosmovie.model.Result>, val context: Context): RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.top_rated_movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.title.text = items.get(position).title
        holder.userrating.text =  items.get(position).voteAverage.toString()
        val poster = "https://image.tmdb.org/t/p/w185/" + items.get(position).posterPath
        Glide.with(context)
            .load(poster)
            .placeholder(R.drawable.backimage)
            .into(holder.imageView)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movieResult",items.get(position))
            context.startActivity(intent)
        })
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title = view.titleTV
        val userrating  = view.userratingTV
        val imageView = view.posterimageview
    }
}