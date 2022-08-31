package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.network.request.SnackCriteria

data class CheckoutMovieInfoVO(
    @SerializedName("id")
    val bookingId: Int? = 0,

    @SerializedName("booking_no")
    val bookingNo: String? = "",

    @SerializedName("booking_date")
    val bookingDate: String? = "",

    @SerializedName("row")
    val seatRows: String? = "",

    @SerializedName("seat")
    val seatNames: String = "",

    @SerializedName("total_seat")
    val seatCount: Int? = 0,

    @SerializedName("total")
    val totalPrice: Int? = 0,

    @SerializedName("movie_id")
    val movieId: Int? = 0,

    @SerializedName("cinema_id")
    val cinemaId: Int? = 0,

    @SerializedName("username")
    val userName: String? = "",

    @SerializedName("timeslot")
    val timeslot: List<TimeslotVO>? = listOf(),

    @SerializedName("snacks")
    val snackList: List<SnackVO>? = listOf(),

    @SerializedName("qr_code")
    val qrCodePath: String? = "",
)
