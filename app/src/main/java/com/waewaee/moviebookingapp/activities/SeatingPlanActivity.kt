package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.MovieSeatAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import kotlinx.android.synthetic.main.activity_seating_plan.*

class SeatingPlanActivity : AppCompatActivity() {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String

    private var timeslotId: Int = 0
    private val mAdapter= MovieSeatAdapter()
    private val mCinemaModel: CinemaModel = CinemaModelImpl
    
    companion object {
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"

        fun newIntent(context: Context, movieName: String, movieDuration: String, movieDate: String, movieTime: String, cinemaName: String, timeslotId: Int): Intent {
            val intent =  Intent(context, SeatingPlanActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)

        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        timeslotId = intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0) ?: 0


        setUpSeatRecyclerView()
        setUpListeners()
        bindMovieHeader()
    }

    private fun bindMovieHeader() {
        tvBookedMovieName.text = movieName
        tvBookedCinemaName.text = cinemaName
        tvBookedDateAndTime.text = "$movieDate, $movieTime"
    }

    private fun setUpSeatRecyclerView() {
        rvSeats.adapter = mAdapter
        rvSeats.layoutManager = GridLayoutManager(this, 10)
        requestSeatData()
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
//            startActivity(ChooseSnackActivity.newIntent(this))
        }
    }

    private fun requestSeatData() {
        mCinemaModel.getSeatingPlan(
            date = movieDate,
            timeslotId = timeslotId,
            onSuccess = {
                val flatten = it.flatten()
                mAdapter.setNewData(it.flatten())
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }
}