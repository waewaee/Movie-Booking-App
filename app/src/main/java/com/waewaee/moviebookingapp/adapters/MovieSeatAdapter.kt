package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.MovieSeatVO
import com.waewaee.moviebookingapp.delegates.SeatDelegate
import com.waewaee.moviebookingapp.view.holders.MovieSeatViewHolder

class MovieSeatAdapter(private var mMovieSeats: List<MovieSeatVO> = listOf(), private val mDelegate: SeatDelegate): RecyclerView.Adapter<MovieSeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_movie_seat, parent, false)
        return MovieSeatViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
        if (mMovieSeats.isNotEmpty()) {
            holder.bindData(mMovieSeats[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieSeats.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieSeats: List<MovieSeatVO>) {
        this.mMovieSeats = movieSeats
        notifyDataSetChanged()
    }
}