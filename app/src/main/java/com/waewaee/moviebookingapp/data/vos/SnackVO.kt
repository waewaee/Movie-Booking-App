package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class SnackVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val snackPrice: Int?,

    @SerializedName("image")
    val imagePath: String?,

    var totalPrice: Int = 0,
    var snackCount: Int = 0,

)
