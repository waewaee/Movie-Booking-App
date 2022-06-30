package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.CinemaViewHolder
import com.waewaee.moviebookingapp.view.holders.ShowTimeViewHolder

class ShowTimeAdapter: RecyclerView.Adapter<ShowTimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_showtime, parent, false)
        return ShowTimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShowTimeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}