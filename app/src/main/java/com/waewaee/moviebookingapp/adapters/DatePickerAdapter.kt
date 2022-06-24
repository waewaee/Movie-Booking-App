package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.DatePickerViewHolder

class DatePickerAdapter: RecyclerView.Adapter<DatePickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatePickerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_date_picker, parent, false)
        return DatePickerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DatePickerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}