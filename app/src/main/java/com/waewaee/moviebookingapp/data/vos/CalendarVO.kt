package com.waewaee.moviebookingapp.data.vos

data class CalendarVO(
    val dayOfWeek: String?,
    val dateOfMonth: Int?,
    var isSelected: Boolean = false,
)
