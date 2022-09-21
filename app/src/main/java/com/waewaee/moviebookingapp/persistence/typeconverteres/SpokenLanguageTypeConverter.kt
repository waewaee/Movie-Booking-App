package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages: List<SpokenLanguageVO>?) : String {
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLanguagesJsonString: String) : List<SpokenLanguageVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguageVO>?>() {}.type
        return Gson().fromJson(spokenLanguagesJsonString, spokenLanguageListType)
    }
}