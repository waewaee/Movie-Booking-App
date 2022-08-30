package com.waewaee.moviebookingapp.adapters

import com.waewaee.moviebookingapp.data.vos.EmptyCardVO
import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.VisaCardDelegate
import kotlinx.android.synthetic.main.view_item_carousel.view.*

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    2019-06-14
 * Email:   alirezat775@gmail.com
 */

class CarouselAdapter(private val mDelegate: VisaCardDelegate) : CarouselAdapter() {

    private val EMPTY_ITEM = 0
    private val NORMAL_ITEM = 1
    private var vh: CarouselViewHolder? = null

    lateinit var mCardList: List<VisaCardVO>

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
            vh = MyViewHolder(v, mDelegate)
            vh as MyViewHolder
        } else {
            val v = inflater.inflate(R.layout.view_item_empty_carousel, parent, false)
            vh = EmptyViewHolder(v)
            vh as EmptyViewHolder
        }
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                val model = getItems()[position] as VisaCardVO
                holder.bindData(model)

            }
            else -> {
                val model = getItems()[position] as EmptyCardVO
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cardList: List<VisaCardVO>) {
        mCardList = cardList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View, private val mDelegate: VisaCardDelegate) : CarouselViewHolder(itemView) {

        lateinit var mCard: VisaCardVO

        init {
            itemView.setOnClickListener {
                mDelegate.onTapCard(mCard.id ?: 0)
            }
        }


        fun bindData(card: VisaCardVO){
            mCard = card

            var cardNumber = card.cardNumber ?: ""
            var list = mutableListOf<String>()
            for (i in 0..3) { list.add(cardNumber.substring(i*4, (i+1)*4))}
            cardNumber = list.joinToString(" ")

            itemView.tvFirstFourDigits.text = cardNumber
            itemView.tvSecondFourDigits.visibility = GONE
            itemView.tvThirdFourDigits.visibility = GONE
            itemView.tvFourthFourDigits.visibility = GONE

            itemView.tvCardHolderName.text = card.cardHolder
            itemView.tvExpiresDate.text = card.expirationDate

            if (card.isSelected) {
                itemView.layoutVisaCard.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            } else {
                itemView.layoutVisaCard.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.color_visa_card))
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : CarouselViewHolder(itemView) {
    }

}