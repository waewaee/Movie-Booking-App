package com.waewaee.moviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.view.holders.PaymentMethodViewHolder
import com.waewaee.moviebookingapp.view.holders.SnackViewHolder

class PaymentMethodAdapter: RecyclerView.Adapter<PaymentMethodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_item_payment_method, parent, false)
        return PaymentMethodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}