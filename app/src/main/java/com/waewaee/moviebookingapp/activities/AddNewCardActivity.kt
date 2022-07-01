package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.moviebookingapp.R
import kotlinx.android.synthetic.main.activity_add_new_card.*

class AddNewCardActivity : AppCompatActivity() {

    companion object{
        fun  newIntent(context: Context): Intent {
            return Intent(context, AddNewCardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        btnConfirm.setOnClickListener {
            super.onBackPressed()
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}