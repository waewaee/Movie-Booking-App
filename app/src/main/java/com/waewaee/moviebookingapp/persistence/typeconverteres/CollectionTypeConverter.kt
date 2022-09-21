package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collectionVO: CollectionVO?) : String {
        return Gson().toJson(collectionVO)
    }

    @TypeConverter
    fun toCollectionVO(commentListJsonString: String) : CollectionVO? {
        val collectionVOType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(commentListJsonString, collectionVOType)
    }
}