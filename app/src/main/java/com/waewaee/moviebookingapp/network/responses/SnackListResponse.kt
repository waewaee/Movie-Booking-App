package com.waewaee.moviebookingapp.network.responses

import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.data.vos.SnackVO

data class SnackListResponse(
    @SerializedName("code")
    val code: Int? = 0,

    @SerializedName("message")
    val message: String? = "",

    @SerializedName("data")
    val snackList: List<SnackVO>? = listOf(),
)
