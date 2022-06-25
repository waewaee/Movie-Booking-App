package com.waewaee.moviebookingapp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.MovieViewHolderDelegate
import com.waewaee.moviebookingapp.adapters.MovieAdapter
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: MovieViewHolderDelegate

    override fun onFinishInflate() {
//        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate,
                              titleText: String = context.getString(R.string.default_title_movie_list_view_pod)) {
        mDelegate = delegate
        tvTitle.text = titleText
        setUpMovieRecyclerView()
    }

    private fun setUpMovieRecyclerView() {
        val adapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}