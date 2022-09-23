package com.waewaee.moviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waewaee.moviebookingapp.data.vos.SnackVO

@Dao
interface SnackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnacks(snackList: List<SnackVO>)

    @Query("SELECT * FROM snacks")
    fun getAllSnacks() : List<SnackVO>

    @Query("DELETE FROM snacks")
    fun deleteSnacks()
}