package com.waewaee.moviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.data.vos.MovieSeatRowVO
import com.waewaee.moviebookingapp.data.vos.MovieSeatVO

data class SeatingPlanResponse(

    @SerializedName("code")
    val code: Int? = 0,

    @SerializedName("message")
    val message: String? = "",

//    @SerializedName("data")
//    val data: List<MovieSeatRowVO>? = listOf(),

    @SerializedName("data")
    val data: List<List<MovieSeatVO>>? = listOf(listOf()),
)
