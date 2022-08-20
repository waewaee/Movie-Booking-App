package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.ActorVO
import com.waewaee.moviebookingapp.view.holders.CastViewHolder
import com.waewaee.moviebookingapp.view.holders.GenreChipViewHolder

class CastAdapter: RecyclerView.Adapter<CastViewHolder>() {

    private var mActorList: List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_cast, parent, false)
        return CastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (mActorList.isNotEmpty()) {
            holder.bindData(mActorList[position])
        }
    }

    override fun getItemCount(): Int {
//        return mActorList.count()
        return 5
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorList: List<ActorVO>) {
        mActorList= actorList
        notifyDataSetChanged()
    }
}