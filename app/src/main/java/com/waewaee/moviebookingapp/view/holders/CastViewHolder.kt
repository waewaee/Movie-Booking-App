package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.waewaee.moviebookingapp.data.vos.ActorVO
import com.waewaee.moviebookingapp.utils.MOVIE_IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_cast.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(actor: ActorVO) {
        Glide.with(itemView.context)
            .load("$MOVIE_IMAGE_BASE_URL${actor.profilePath}")
            .into(itemView.ivCastImage)

        itemView.tvCastName.text = actor.name
    }
}