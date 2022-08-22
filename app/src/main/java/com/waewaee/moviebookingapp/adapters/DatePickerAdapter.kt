package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.CalendarVO
import com.waewaee.moviebookingapp.delegates.CalendarViewHolderDelegate
import com.waewaee.moviebookingapp.view.holders.DatePickerViewHolder

class DatePickerAdapter(private var mTwoWeeksDates: List<CalendarVO>,
private var  mDelegate: CalendarViewHolderDelegate): RecyclerView.Adapter<DatePickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatePickerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_date_picker, parent, false)
        return DatePickerViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: DatePickerViewHolder, position: Int) {
        if (mTwoWeeksDates.isNotEmpty()) {
            holder.bindData(mTwoWeeksDates[position])
        }
    }

    override fun getItemCount(): Int {
        return 14
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(twoWeeksDates: List<CalendarVO>) {
        mTwoWeeksDates = twoWeeksDates
        notifyDataSetChanged()
    }
}