package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CinemaAdapter
import com.waewaee.moviebookingapp.adapters.DatePickerAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.vos.CinemaVO
import com.waewaee.moviebookingapp.delegates.CalendarViewHolderDelegate
import com.waewaee.moviebookingapp.delegates.TimeslotDelegate
import com.waewaee.moviebookingapp.dummy.TWO_WEEKS_DATES
import kotlinx.android.synthetic.main.activity_book_ticket.*

class BookTicketActivity : AppCompatActivity(), CalendarViewHolderDelegate, TimeslotDelegate {

    lateinit var datePickerAdapter: DatePickerAdapter
    lateinit var cinemaAdapter: CinemaAdapter

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var cinemaList: List<CinemaVO>

    private val mCinemaModel: CinemaModel = CinemaModelImpl
    private var movieId: Int = 0
    private var movieTimeslotId: Int = 0


    companion object {
        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"

        fun newIntent(context: Context, movieId: Int, movieName: String, movieDuration: String): Intent {
            val intent = Intent(context, BookTicketActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ticket)

        setUpDatePickerRecyclerView()
        setUpCinemaRecyclerView()
        setUpListeners()

        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
    }

    private fun requestCinemaData() {
        mCinemaModel.getCinemaTimeslots(
            date = movieDate,
            onSuccess = {
                cinemaAdapter.setNewData(it)
                cinemaList = it
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
            if (movieDate.isNotEmpty() && movieTime.isNotEmpty() && cinemaName.isNotEmpty()) {
                startActivity(SeatingPlanActivity.newIntent(this, movieName, movieDuration, movieDate, movieTime, cinemaName, movieTimeslotId))
            }
        }
    }

    private fun setUpCinemaRecyclerView() {
        cinemaAdapter = CinemaAdapter(this)
        rvCinema.adapter = cinemaAdapter
        rvCinema.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpDatePickerRecyclerView() {
        datePickerAdapter = DatePickerAdapter(TWO_WEEKS_DATES, this)
        rvDatePicker.adapter = datePickerAdapter
        rvDatePicker.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
    }

    override fun onTapDate(date: Int) {
        movieDate = "2022-08-$date"
        requestCinemaData()

        TWO_WEEKS_DATES.map {
            it.isSelected = it.dateOfMonth == date
        }
        datePickerAdapter.setNewData(TWO_WEEKS_DATES)
    }

    override fun onTapTimeslot(startTime: String, cinemaId: Int, timeslotId: Int) {
        movieTime = startTime
        cinemaList.map {
            if (it.cinemaId == cinemaId) {
                cinemaName = it.cinema ?: ""
                it.timeslots?.map {
                    it.isSelected = it.startTime == startTime
                    movieTimeslotId = it.cinemaDayTimeslotId ?: 0
                }
            } else {
                it.timeslots?.map {
                    it.isSelected = false
                }
            }
        }
        cinemaAdapter.setNewData(cinemaList)
    }
}