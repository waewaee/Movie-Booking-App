package com.waewaee.moviebookingapp.data.vos

import alirezat775.lib.carouselview.CarouselModel

class EmptyCardVO constructor(private val text: String) : CarouselModel() {

    fun getText(): String {
        return text
    }
}