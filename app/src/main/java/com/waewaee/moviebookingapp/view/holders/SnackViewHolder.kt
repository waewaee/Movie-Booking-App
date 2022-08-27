package com.waewaee.moviebookingapp.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.delegates.SnackDelegate
import kotlinx.android.synthetic.main.view_item_snack.view.*

class SnackViewHolder(itemView: View, private val mDelegate: SnackDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mSnack: SnackVO

    init {
        itemView.btnAdd.setOnClickListener {
            mDelegate.onTapPlus(mSnack.id ?: 0, mSnack.snackPrice ?: 0)
        }
        itemView.btnMinus.setOnClickListener {
            mDelegate.onTapMinus(mSnack.id ?: 0, mSnack.snackPrice ?: 0)
        }
    }

    fun bindData(snack: SnackVO) {
        mSnack = snack
        itemView.tvSnackSetName.text = snack.name
        itemView.tvSnackSetDetails.text = snack.description
        itemView.tvPrice.text = "${snack.snackPrice}$"
        itemView.btnNumber.text = snack.snackCount.toString()
    }
}