package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.adapters.ShowTimeAdapter
import com.waewaee.moviebookingapp.data.vos.CinemaVO
import com.waewaee.moviebookingapp.delegates.TimeslotDelegate
import kotlinx.android.synthetic.main.view_item_cinema.*
import kotlinx.android.synthetic.main.view_item_cinema.view.*

class CinemaViewHolder(itemView: View, private val mDelegate: TimeslotDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mCinema: CinemaVO? = null
    lateinit var showTimeAdapter: ShowTimeAdapter

    init {
        showTimeAdapter = ShowTimeAdapter(mDelegate)
        itemView.rvShowTime.adapter = showTimeAdapter
        itemView.rvShowTime.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL,false)
    }

    fun bindData(cinema: CinemaVO) {
        mCinema= cinema
        itemView.tvCinemaName.text = mCinema?.cinema ?: ""

        showTimeAdapter.setCinemaId(mCinema?.cinemaId ?: 0)
        showTimeAdapter.setNewData(mCinema?.timeslots ?: listOf())
    }
}