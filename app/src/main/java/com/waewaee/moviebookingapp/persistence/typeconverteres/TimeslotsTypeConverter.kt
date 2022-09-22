package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.TimeslotVO

class TimeslotsTypeConverter {
    @TypeConverter
    fun toString(timeslotsList: List<TimeslotVO>?) : String {
        return Gson().toJson(timeslotsList)
    }

    @TypeConverter
    fun toTimeslotsList(timeslotsListJsonString: String) : List<TimeslotVO>? {
        val timeslotsListType = object : TypeToken<List<TimeslotVO>?>() {}.type
        return Gson().fromJson(timeslotsListJsonString, timeslotsListType)
    }

}
