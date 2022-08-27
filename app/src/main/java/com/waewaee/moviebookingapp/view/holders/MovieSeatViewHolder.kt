package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.MovieSeatVO
import com.waewaee.moviebookingapp.delegates.SeatDelegate
import kotlinx.android.synthetic.main.view_item_movie_seat.view.*

class MovieSeatViewHolder(itemView: View, private val mDelegate: SeatDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mSeat: MovieSeatVO

    init {
        itemView.setOnClickListener {
            mDelegate.onTapSeat(mSeat.title ?: "", mSeat.price ?: 0)
        }
    }

    fun bindData(data: MovieSeatVO) {
        mSeat = data

        when {
            data.isMovieSeatAvailable() == true -> {
                if (data.isSelected) {
                    itemView.tvMovieSeatTitle.text = data.title?.substring(2)
                    itemView.tvMovieSeatTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    itemView.background = ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.background_movie_seat_selected
                    )
                } else {
                    itemView.tvMovieSeatTitle.text = ""
                    itemView.background = ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.background_movie_seat_available
                    )
                }

            }

            data.isMovieSeatTaken() == true -> {
                itemView.tvMovieSeatTitle.text = ""
                itemView.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_movie_seat_taken
                )
            }

            data.isMovieSeatRowTitle() == true -> {
                itemView.tvMovieSeatTitle.text = data.symbol.toString()
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }

            else -> {
//                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.tvMovieSeatTitle.text = ""
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