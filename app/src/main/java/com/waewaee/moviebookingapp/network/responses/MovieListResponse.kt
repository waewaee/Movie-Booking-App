package com.waewaee.moviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.data.vos.DateVO
import com.waewaee.moviebookingapp.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("dates")
    val dates: DateVO?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieVO>?
)