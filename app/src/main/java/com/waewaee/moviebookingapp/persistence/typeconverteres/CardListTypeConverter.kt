package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.VisaCardVO

class CardListTypeConverter {
    @TypeConverter
    fun toString(cardList: List<VisaCardVO>?) : String {
        return Gson().toJson(cardList)
    }

    @TypeConverter
    fun toCardList(cardListJsonString: String) : List<VisaCardVO>? {
        val cardListType = object : TypeToken<List<VisaCardVO>?>() {}.type
        return Gson().fromJson(cardListJsonString, cardListType)
    }
}