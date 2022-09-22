package com.waewaee.moviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waewaee.moviebookingapp.data.vos.CinemaVO

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemas(cinemas: List<CinemaVO>)

    @Query("SELECT * FROM cinemas")
    fun getAllCinemas() : List<CinemaVO>

    @Query("SELECT * FROM cinemas WHERE date = :date")
    fun getCinemaByDate(date: String) : List<CinemaVO>

    @Query("DELETE FROM cinemas")
    fun deleteAllCinemas()
}