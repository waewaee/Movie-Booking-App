package com.waewaee.moviebookingapp.adapters

import alirezat775.carouselview.sample.EmptyCardVO
import alirezat775.carouselview.sample.VisaCardVO
import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.waewaee.moviebookingapp.R
import kotlinx.android.synthetic.main.view_item_carousel.view.*
import kotlinx.android.synthetic.main.view_item_empty_carousel.view.*

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    2019-06-14
 * Email:   alirezat775@gmail.com
 */

class CarouselAdapter : CarouselAdapter() {

    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1

    private var vh: CarouselViewHolder? = null

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is EmptyCardVO -> EMPTY_ITEM
            else -> NORMAL_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == NORMAL_ITEM) {
            val v = inflater.inflate(R.layout.view_item_carousel, parent, false)
            vh = MyViewHolder(v)
            vh as MyViewHolder
        } else {
            val v = inflater.inflate(R.layout.view_item_empty_carousel, parent, false)
            vh = EmptyMyViewHolder(v)
            vh as EmptyMyViewHolder
        }
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                vh = holder
                val model = getItems()[position] as VisaCardVO
            }
            else -> {
                vh = holder
                val model = getItems()[position] as EmptyCardVO
            }
        }
    }

    inner class MyViewHolder(itemView: View) : CarouselViewHolder(itemView) {
    }

    inner class EmptyMyViewHolder(itemView: View) : CarouselViewHolder(itemView) {
    }

}