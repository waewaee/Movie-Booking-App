package com.waewaee.moviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waewaee.moviebookingapp.data.vos.UserVO
import com.waewaee.moviebookingapp.persistence.daos.UserDao

@Database(entities = [UserVO::class], version = 3, exportSchema = false)
abstract class CinemaDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "DB_CINEMA"

        var dbInstance: CinemaDatabase? = null

        fun getDbInstance(context: Context) : CinemaDatabase? {
            when(dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, CinemaDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun userDao(): UserDao
}