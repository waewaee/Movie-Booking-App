package com.waewaee.moviebookingapp.data.vos

import alirezat775.lib.carouselview.CarouselModel
import com.google.gson.annotations.SerializedName

data class VisaCardVO (

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("card_number")
    val cardNumber: String? = "",

    @SerializedName("card_holder")
    val cardHolder: String? = "",

    @SerializedName("expiration_date")
    val expirationDate: String? = "",

    @SerializedName("card_type")
    val cardType: String? = "",

    ) : CarouselModel() {

}
