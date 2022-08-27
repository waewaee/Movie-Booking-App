package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieSeatVO(
    @SerializedName("seat_name")
    var title: String?,

    @SerializedName("type")
     val type: String?,

    @SerializedName("id")
    val id: Int? = 0,

    @SerializedName("symbol")
    val symbol: Char? = '-',

    @SerializedName("price")
    val price: Int? = 0,

    var isSelected: Boolean = false

) {

    fun isMovieSeatAvailable(): Boolean?{
        return type == SEAT_TYPE_AVAILABLE
    }

    fun isMovieSeatTaken(): Boolean? {
        return type == SEAT_TYPE_TAKEN
    }

    fun isMovieSeatRowTitle(): Boolean? {
        return type == SEAT_TYPE_TEXT
    }
}

const val SEAT_TYPE_AVAILABLE = "available"
const val SEAT_TYPE_TAKEN = "taken"
const val SEAT_TYPE_TEXT = "text"
const val SEAT_TYPE_EMPTY = "space"
