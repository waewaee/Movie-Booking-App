package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class TimeslotVO (

    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int?,

    @SerializedName("start_time")
    val startTime: String?,

    var isSelected: Boolean = false
)