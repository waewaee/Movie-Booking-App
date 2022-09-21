package com.waewaee.moviebookingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnStart.setOnClickListener {
            finish()

           if (CinemaModelImpl.checkLoginUser()) {
               startActivity(MovieListActivity.newIntent(this))
           } else {
               startActivity(LoginActivity.newIntent(this))
           }
        }
    }
}