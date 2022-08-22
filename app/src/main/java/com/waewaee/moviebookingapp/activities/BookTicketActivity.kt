package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CinemaAdapter
import com.waewaee.moviebookingapp.adapters.DatePickerAdapter
import com.waewaee.moviebookingapp.data.vos.CalendarVO
import com.waewaee.moviebookingapp.delegates.CalendarViewHolderDelegate
import com.waewaee.moviebookingapp.dummy.TWO_WEEKS_DATES
import kotlinx.android.synthetic.main.activity_book_ticket.*

class BookTicketActivity : AppCompatActivity(), CalendarViewHolderDelegate {

//    private var mTwoWeeksDates: List<CalendarVO>? = null
    lateinit var datePickerAdapter: DatePickerAdapter

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
        datePickerAdapter = DatePickerAdapter(TWO_WEEKS_DATES, this)
        rvDatePicker.adapter = datePickerAdapter
        rvDatePicker.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
    }

    override fun onTapDate(date: Int) {
        TWO_WEEKS_DATES.map {
            it.isSelected = it.dateOfMonth == date
        }
        datePickerAdapter.setNewData(TWO_WEEKS_DATES)
    }
}