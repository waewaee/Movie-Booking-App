package com.waewaee.moviebookingapp.data.vos

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.waewaee.moviebookingapp.persistence.typeconverteres.TimeslotsTypeConverter

@Entity(tableName = "cinemas")
@TypeConverters(
    TimeslotsTypeConverter::class
)

data class CinemaVO(
    @SerializedName("cinema_id")
    @ColumnInfo( name = "cinema_id")
    val cinemaId: Int?,

    @SerializedName("cinema")
    @ColumnInfo(name = "cinema")
    val cinema: String?,

    @SerializedName("timeslots")
    @ColumnInfo(name = "timeslots")
    val timeslots: List<TimeslotVO>?,

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "key")
    @PrimaryKey
    var key: String = "",
)
