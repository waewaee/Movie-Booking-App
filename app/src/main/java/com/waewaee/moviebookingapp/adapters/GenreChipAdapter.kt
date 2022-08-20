package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.GenreChipViewHolder
import com.waewaee.themovieapp.data.vos.GenreVO

class GenreChipAdapter: RecyclerView.Adapter<GenreChipViewHolder>() {

    private var mGenreList: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_genre_chip, parent, false)
        return GenreChipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GenreChipViewHolder, position: Int) {
        if (mGenreList.isNotEmpty()) {
            holder.bindData(mGenreList[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genreList: List<GenreVO>) {
        mGenreList = genreList
        notifyDataSetChanged()
    }
}