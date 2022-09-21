package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.ProductionCountryVO

class ProductionCountryTypeConverter {
    @TypeConverter
    fun toString(productionCountryList: List<ProductionCountryVO>?) : String {
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountries(productionCountryJsonString: String) : List<ProductionCountryVO>? {
        val productionCountryListType = object : TypeToken<List<ProductionCountryVO>?>() {}.type
        return Gson().fromJson(productionCountryJsonString, productionCountryListType)
    }
}