package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class PaymentMethodVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val paymentName: String?,

    @SerializedName("description")
    val description: String?,

    var isSelected: Boolean = false
)
