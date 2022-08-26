package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieSeatVO(
    @SerializedName("seat_name")
    val title: String?,

    @SerializedName("type")
    private val type: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("symbol")
    val symbol: Char?,

    @SerializedName("price")
    val price: Int?,

) {

    fun isMovieSeatAvailable(): Boolean?{
        return type === SEAT_TYPE_AVAILABLE
    }

    fun isMovieSeatTaken(): Boolean? {
        return type === SEAT_TYPE_TAKEN
    }

    fun isMovieSeatRowTitle(): Boolean? {
        return type === SEAT_TYPE_TEXT
    }
}

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TAKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"
