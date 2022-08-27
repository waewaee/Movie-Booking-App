package com.waewaee.moviebookingapp.delegates

interface SnackDelegate {
    fun onTapPlus(id: Int, price: Int)
    fun onTapMinus(id: Int, price: Int)
}