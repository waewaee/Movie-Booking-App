package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.MovieSeatVO
import kotlinx.android.synthetic.main.view_item_movie_seat.view.*

class MovieSeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: MovieSeatVO) {
        when {
            data.isMovieSeatAvailable() == true -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_available
                )
            }

            data.isMovieSeatTaken() == true -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }

            data.isMovieSeatRowTitle() == true -> {
                itemView.tvMovieSeatTitle.visibility = View.VISIBLE
                itemView.tvMovieSeatTitle.text = data.title
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }

            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }

        }
    }
}