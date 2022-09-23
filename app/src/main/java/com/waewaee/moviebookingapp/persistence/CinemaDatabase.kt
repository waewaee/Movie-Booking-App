package com.waewaee.moviebookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.waewaee.moviebookingapp.data.vos.*
import com.waewaee.moviebookingapp.persistence.daos.*

@Database(entities = [UserVO::class, MovieVO::class, ActorVO::class, CinemaVO::class, SnackVO::class, PaymentMethodVO::class], version = 12, exportSchema = false)
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
    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun cinemaDao(): CinemaDao
    abstract fun snackDao(): SnackDao
    abstract fun paymentMethodDao(): PaymentMethodDao
}