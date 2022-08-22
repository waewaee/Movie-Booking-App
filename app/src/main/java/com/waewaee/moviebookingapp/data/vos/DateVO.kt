package com.waewaee.themovieapp.data.vos

import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek

data class DateVO(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?,
        )