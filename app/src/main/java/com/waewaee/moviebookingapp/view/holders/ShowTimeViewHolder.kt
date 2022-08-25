package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.TimeslotVO
import com.waewaee.moviebookingapp.delegates.TimeslotDelegate
import kotlinx.android.synthetic.main.view_item_showtime.view.*

class ShowTimeViewHolder(itemView: View, private var mDelegate: TimeslotDelegate, private var mCinemaId: Int) : RecyclerView.ViewHolder(itemView) {

    private var mShowTime: TimeslotVO? = null

    init {
        itemView.setOnClickListener {
            mDelegate.onTapTimeslot(mShowTime?.startTime ?: "", mCinemaId)
        }
    }

    fun bindData(showTime: TimeslotVO) {
        mShowTime = showTime
        itemView.tvShowtime.text = mShowTime?.startTime ?: ""

        if (showTime.isSelected) {
            itemView.setBackgroundDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.background_showtime_chosen))
            itemView.tvShowtime.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorWhite))
        } else {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorWhite))
            itemView.tvShowtime.setTextColor(ContextCompat.getColor(itemView.context, R.color.secondary_text))
        }
    }
}