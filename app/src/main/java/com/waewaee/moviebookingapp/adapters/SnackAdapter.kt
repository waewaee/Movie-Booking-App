package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.SnackViewHolder

class SnackAdapter: RecyclerView.Adapter<SnackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_snack, parent, false)
        return SnackViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}