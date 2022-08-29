package com.waewaee.moviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.data.vos.PaymentMethodVO
import com.waewaee.moviebookingapp.data.vos.VisaCardVO

data class AddNewCardResponse(
    @SerializedName("code")
    val code: Int? = 0,

    @SerializedName("message")
    val message: String? = "",

    @SerializedName("data")
    val card: List<VisaCardVO>? = listOf(),
)
