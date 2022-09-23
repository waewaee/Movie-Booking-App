package com.waewaee.moviebookingapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payment_methods")
data class PaymentMethodVO(
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val paymentName: String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "is_selected")
    var isSelected: Boolean = false
)
