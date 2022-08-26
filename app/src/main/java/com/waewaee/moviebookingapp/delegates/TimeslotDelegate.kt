package com.waewaee.moviebookingapp.delegates

interface TimeslotDelegate {
    fun onTapTimeslot(startTime: String, cinemaId: Int, timeslotId: Int)
}