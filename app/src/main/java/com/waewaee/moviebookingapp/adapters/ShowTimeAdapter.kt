package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.TimeslotVO
import com.waewaee.moviebookingapp.delegates.TimeslotDelegate
import com.waewaee.moviebookingapp.view.holders.ShowTimeViewHolder

class ShowTimeAdapter(private var mDelegate: TimeslotDelegate): RecyclerView.Adapter<ShowTimeViewHolder>() {

    private var mShowTimeList: List<TimeslotVO> = listOf()
    private var mCinemaId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_showtime, parent, false)
        return ShowTimeViewHolder(itemView, mDelegate, mCinemaId)
    }

    override fun onBindViewHolder(holder: ShowTimeViewHolder, position: Int) {
        if (mShowTimeList.isNotEmpty()) {
            holder.bindData(mShowTimeList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShowTimeList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(showTimeList: List<TimeslotVO>) {
        mShowTimeList = showTimeList
        notifyDataSetChanged()
    }

    fun setCinemaId(cinemaId: Int) {
        mCinemaId = cinemaId
    }
}