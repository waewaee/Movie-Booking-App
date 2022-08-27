package com.waewaee.moviebookingapp.view.holders

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.PaymentMethodVO
import com.waewaee.moviebookingapp.delegates.PaymentMethodDelegate
import kotlinx.android.synthetic.main.view_item_date_picker.view.*
import kotlinx.android.synthetic.main.view_item_payment_method.view.*

class PaymentMethodViewHolder(itemView: View, private val mDelegate: PaymentMethodDelegate) : RecyclerView.ViewHolder(itemView) {

    lateinit var mMethod: PaymentMethodVO

    init {
        itemView.setOnClickListener {
            mDelegate.onTapPaymentMethod(mMethod.id ?: 0)
        }
    }

    fun bindData(method: PaymentMethodVO) {
        mMethod = method
        itemView.tvCardType.text = method.paymentName
        itemView.tvCardTypeDetails.text = method.description

        if (method.isSelected) {
            DrawableCompat.setTint(itemView.ivCardImage.drawable, ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            itemView.tvCardType.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
            itemView.tvCardTypeDetails.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
        } else {
            DrawableCompat.setTint(itemView.ivCardImage.drawable, ContextCompat.getColor(itemView.context, R.color.md_grey_500))
            itemView.tvCardType.setTextColor(ContextCompat.getColor(itemView.context, R.color.md_grey_500))
            itemView.tvCardTypeDetails.setTextColor(ContextCompat.getColor(itemView.context, R.color.md_grey_500))
        }
    }
}