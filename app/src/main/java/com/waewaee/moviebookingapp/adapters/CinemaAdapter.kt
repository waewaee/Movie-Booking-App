package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.CinemaVO
import com.waewaee.moviebookingapp.delegates.TimeslotDelegate
import com.waewaee.moviebookingapp.view.holders.CinemaViewHolder

class CinemaAdapter(private val mDelegate: TimeslotDelegate): RecyclerView.Adapter<CinemaViewHolder>() {

    private var mCinemaList: List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_cinema, parent, false)
        return CinemaViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        if (mCinemaList.isNotEmpty()) {
            holder.bindData(mCinemaList[position])
        }
    }

    override fun getItemCount(): Int {
        return mCinemaList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }
}