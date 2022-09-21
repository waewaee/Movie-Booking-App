package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.data.vos.GenreVO
import kotlinx.android.synthetic.main.view_item_genre_chip.view.*

class GenreChipViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindData(genre: GenreVO) {
        itemView.tvGenreChip.text = genre.name
    }
}