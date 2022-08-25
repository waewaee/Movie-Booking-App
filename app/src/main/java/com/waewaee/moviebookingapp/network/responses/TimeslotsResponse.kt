package com.waewaee.moviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.data.vos.CinemaVO

data class TimeslotsResponse(

    @SerializedName("code")
    val code: Int? = 0,

    @SerializedName("message")
    val message: String? = "",

    @SerializedName("data")
    val cinemaList: List<CinemaVO>? = listOf(),
)
