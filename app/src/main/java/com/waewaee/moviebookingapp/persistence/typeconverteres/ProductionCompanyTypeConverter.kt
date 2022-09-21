package com.waewaee.moviebookingapp.persistence.typeconverteres

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.waewaee.moviebookingapp.data.vos.ProductionCompanyVO

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompanyList: List<ProductionCompanyVO>?) :String {
        return Gson().toJson(productionCompanyList)
    }

    @TypeConverter
    fun toProductionCompanies(productionCompaniesJsonString: String) : List<ProductionCompanyVO>? {
        val productionCompanyListType = object : TypeToken<List<ProductionCompanyVO>?>() {}.type
        return Gson().fromJson(productionCompaniesJsonString, productionCompanyListType)
    }
}