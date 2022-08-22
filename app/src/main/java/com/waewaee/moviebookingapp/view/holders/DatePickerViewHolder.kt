package com.waewaee.moviebookingapp.view.holders

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.CalendarVO
import com.waewaee.moviebookingapp.delegates.CalendarViewHolderDelegate
import kotlinx.android.synthetic.main.view_item_date_picker.view.*

class DatePickerViewHolder(itemView: View, private var mDelegate: CalendarViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mDate: CalendarVO

    init {
        itemView.setOnClickListener {
            mDelegate.onTapDate(mDate.dateOfMonth ?: 1)
        }
    }

    fun bindData(date: CalendarVO) {
        mDate = date
        itemView.tvDay.text = date.dayOfWeek
        itemView.tvDate.text = date.dateOfMonth.toString()

        mDate.isSelected?.let {
            if (mDate.isSelected!!) {
                itemView.tvDay.setTextColor(Color.WHITE)
                itemView.tvDate.setTextColor(Color.WHITE)
            } else {
                itemView.tvDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.secondary_text))
                itemView.tvDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.secondary_text))
            }
        }
    }
}