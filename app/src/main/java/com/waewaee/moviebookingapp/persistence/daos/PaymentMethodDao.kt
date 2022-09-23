package com.waewaee.moviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waewaee.moviebookingapp.data.vos.PaymentMethodVO

@Dao
interface PaymentMethodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaymentMethods(paymentMethodList: List<PaymentMethodVO>)

    @Query("SELECT * FROM payment_methods")
    fun getAllPaymentMethods() : List<PaymentMethodVO>

    @Query("DELETE FROM payment_methods")
    fun deletePaymentMethods()
}