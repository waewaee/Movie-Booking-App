package com.waewaee.moviebookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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

    @SerializedName("quantity")
    var snackCount: Int = 0,

    @SerializedName("total_price")
    var totalPrice: Int = 0,

): Serializable
