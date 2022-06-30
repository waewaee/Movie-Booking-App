package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.CinemaViewHolder

class CinemaAdapter: RecyclerView.Adapter<CinemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_cinema, parent, false)
        return CinemaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 6
    }
}