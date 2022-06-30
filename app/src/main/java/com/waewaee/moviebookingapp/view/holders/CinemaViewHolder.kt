package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.adapters.ShowTimeAdapter
import kotlinx.android.synthetic.main.view_item_cinema.*
import kotlinx.android.synthetic.main.view_item_cinema.view.*

class CinemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        val showTimeAdapter = ShowTimeAdapter()
        itemView.rvShowTime.adapter = showTimeAdapter
        itemView.rvShowTime.layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL,false)
    }
}