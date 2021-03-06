package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CinemaAdapter
import com.waewaee.moviebookingapp.adapters.DatePickerAdapter
import com.waewaee.moviebookingapp.adapters.ShowTimeAdapter
import kotlinx.android.synthetic.main.activity_book_ticket.*
import kotlinx.android.synthetic.main.view_item_cinema.*

class BookTicketActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, BookTicketActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ticket)

        setUpDatePickerRecyclerView()
        setUpCinemaRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
            startActivity(SeatingPlanActivity.newIntent(this))
        }
    }

    private fun setUpCinemaRecyclerView() {
        val cinemaAdapter = CinemaAdapter()
        rvCinema.adapter = cinemaAdapter
        rvCinema.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpDatePickerRecyclerView() {
        val datePickerAdapter = DatePickerAdapter()
        rvDatePicker.adapter = datePickerAdapter
        rvDatePicker.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
    }
}