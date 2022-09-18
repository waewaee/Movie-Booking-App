package com.waewaee.moviebookingapp

import android.app.Application
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl

class CinemaApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        CinemaModelImpl.initDatabase(applicationContext)
    }
}