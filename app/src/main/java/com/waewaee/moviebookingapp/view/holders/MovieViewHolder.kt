package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.MovieViewHolderDelegate
import com.waewaee.moviebookingapp.utils.MOVIE_IMAGE_BASE_URL
import com.waewaee.moviebookingapp.data.vos.MovieVO
import kotlinx.android.synthetic.main.view_item_movie.view.*

class MovieViewHolder(itemView: View, private val delegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                delegate.onTapMovie(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovie = movie

        Glide.with(itemView.context)
            .load("$MOVIE_IMAGE_BASE_URL${movie.posterPath}")
            .placeholder(R.drawable.john_wick_image)
            .into(itemView.ivMovieImage)

        itemView.tvMovieName.text = movie.title
    }
}