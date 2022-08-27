package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.delegates.SnackDelegate
import com.waewaee.moviebookingapp.view.holders.SnackViewHolder

class SnackAdapter(private val mDelegate: SnackDelegate): RecyclerView.Adapter<SnackViewHolder>() {
    private var mSnackList: List<SnackVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_snack, parent, false)
        return SnackViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {
        if (mSnackList.isNotEmpty()) {
            holder.bindData(mSnackList[position])
        }
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    @SuppressLint("NotifyDatSetChanged")
    fun setNewData(snackList: List<SnackVO>) {
        mSnackList = snackList
        notifyDataSetChanged()
    }
}