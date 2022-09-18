package com.waewaee.moviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.waewaee.moviebookingapp.data.vos.UserVO

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserVO)

    @Query("SELECT * FROM users")
    fun getAllUsers() : List<UserVO>

    @Query("SELECT * FROM users")
    fun getLoginUser() : UserVO?

    @Query("DELETE FROM users")
    fun deleteAllUsers()
}