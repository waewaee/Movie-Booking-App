package com.waewaee.moviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "snacks")
data class SnackVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo( name ="name")
    val name: String?,

    @SerializedName("description")
    @ColumnInfo( name ="description")
    val description: String?,

    @SerializedName("price")
    @ColumnInfo( name ="price")
    val snackPrice: Int?,

    @SerializedName("image")
    @ColumnInfo( name ="image")
    val imagePath: String?,

    @SerializedName("quantity")
    @ColumnInfo( name ="quantity")
    var snackCount: Int = 0,

    @SerializedName("total_price")
    @ColumnInfo( name ="total_price")
    var totalPrice: Int = 0,

): Serializable
