package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieSeatRowVO(
    val row: List<MovieSeatVO>? = listOf()
)
