package com.waewaee.moviebookingapp.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.MovieViewHolderDelegate
import com.waewaee.moviebookingapp.adapters.MovieAdapter
import com.waewaee.moviebookingapp.data.vos.MovieVO
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: MovieViewHolderDelegate
    lateinit var mMovieAdapter: MovieAdapter

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

    fun setData(movieList: List<MovieVO>) {
        mMovieAdapter.setNewData(movieList)
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

}