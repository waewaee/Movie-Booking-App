package com.waewaee.moviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.vos.PaymentMethodVO
import com.waewaee.moviebookingapp.delegates.PaymentMethodDelegate
import com.waewaee.moviebookingapp.view.holders.PaymentMethodViewHolder
import com.waewaee.moviebookingapp.view.holders.SnackViewHolder

class PaymentMethodAdapter(private var mDelegate: PaymentMethodDelegate): RecyclerView.Adapter<PaymentMethodViewHolder>() {

    private var mMethodList: List<PaymentMethodVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_payment_method, parent, false)
        return PaymentMethodViewHolder(itemView, mDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        if (mMethodList.isNotEmpty()) {
            holder.bindData(mMethodList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMethodList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(methodList: List<PaymentMethodVO>) {
        mMethodList = methodList
        notifyDataSetChanged()
    }
}