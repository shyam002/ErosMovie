package com.example.erosmovie.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.erosmovie.R
import com.example.erosmovie.adapter.TopRatedAdapter
import com.example.erosmovie.viewmodel.TopRatedViewModel





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedFragment : Fragment() {
      lateinit var topRated_recyclerview:RecyclerView
      lateinit var progressbar:ProgressBar
    var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    var page:Int = 1
    var lastItem: Int = -1
    lateinit var layoutManager: GridLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_rated, container, false)
        this.activity!!.title = "Top Rated Movies"
        topRated_recyclerview = view.findViewById(R.id.topRated_recyclerview)
        topRated_recyclerview.visibility = View.GONE
        progressbar = view.findViewById(R.id.progressbar)
        val viewModel = ViewModelProviders.of(this).get(TopRatedViewModel::class.java)
        viewModel.getTopRatedMovieList(page)
        viewModel.mresult.observe(this, Observer {
            topRated_recyclerview.visibility = View.VISIBLE
            progressbar.visibility = View.GONE
            topRated_recyclerview.adapter =  TopRatedAdapter(viewModel.mresult.value!!, this.activity!!)
            layoutManager = GridLayoutManager(this.activity, 2)
            topRated_recyclerview.layoutManager = layoutManager
            if(page > 1){
                topRated_recyclerview.scrollToPosition(lastItem)
            }
            lastItem = (viewModel.mresult.value!!.size) - 1
            loading = true
        })

        topRated_recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount()
                    totalItemCount = layoutManager.getItemCount()
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            page += 1
                            viewModel.getTopRatedMovieList(page)
                        }
                   }
                }
            }
        })
        topRated_recyclerview.setOnClickListener(View.OnClickListener {

        })
        return view
    }


}
