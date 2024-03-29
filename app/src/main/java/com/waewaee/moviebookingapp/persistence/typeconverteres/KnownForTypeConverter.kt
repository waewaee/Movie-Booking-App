package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.MovieVO

class KnownForTypeConverter {
    @TypeConverter
    fun toString(knownForList: List<MovieVO>?): String {
        return Gson().toJson(knownForList)
    }

    @TypeConverter
    fun toKnownForList(knownForListJsonString: String) : List<MovieVO>? {
        val knownForListType = object : TypeToken<List<MovieVO>?>() {}.type
        return Gson().fromJson(knownForListJsonString, knownForListType)
    }
}