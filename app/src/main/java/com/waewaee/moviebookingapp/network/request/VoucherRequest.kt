package com.waewaee.moviebookingapp.network.request

data class VoucherRequest(
    val booking_date: String,
    val card_id: Int,
    val cinema_day_timeslot_id: Int,
    val cinema_id: Int,
    val movie_id: Int,
    val row: String,
    val seat_number: String,
    val snacks: List<SnackCriteria>,
    val total_price: Double
)

data class SnackCriteria(
    val id: Int,
    val quantity: Int
)