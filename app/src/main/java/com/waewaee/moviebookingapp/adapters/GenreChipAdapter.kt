package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.GenreChipViewHolder

class GenreChipAdapter: RecyclerView.Adapter<GenreChipViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_genre_chip, parent, false)
        return GenreChipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenreChipViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }
}