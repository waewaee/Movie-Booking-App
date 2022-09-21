package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {
    @TypeConverter
    fun toString(genreIdList: List<Int>?) : String {
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIds(genreIdListJsonString: String) : List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdListJsonString, genreIdsListType)
    }
}