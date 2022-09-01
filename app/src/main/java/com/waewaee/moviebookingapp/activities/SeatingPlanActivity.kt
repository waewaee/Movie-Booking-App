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
import com.waewaee.moviebookingapp.data.vos.MovieSeatVO
import com.waewaee.moviebookingapp.delegates.SeatDelegate
import com.waewaee.moviebookingapp.dummy.DUMMY_SEATS
import kotlinx.android.synthetic.main.activity_seating_plan.*

class SeatingPlanActivity : AppCompatActivity(), SeatDelegate {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var mAdapter: MovieSeatAdapter
    lateinit var seatList: List<MovieSeatVO>
    lateinit var posterPath: String

    private var movieId: Int = 0
    private var cinemaId: Int = 0
    private var timeslotId: Int = 0
    private var ticketCount = 0
    private var seatNames = ""
    private var seatRows = ""
    private var mPrice = 0
    private val mCinemaModel: CinemaModel = CinemaModelImpl
    
    companion object {
        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"
        private val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"

        fun newIntent(context: Context, movieId: Int, movieName: String, movieDuration: String, posterPath: String,  movieDate: String, movieTime: String, cinemaName: String, cinemaId: Int, timeslotId: Int): Intent {
            val intent =  Intent(context, SeatingPlanActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)

        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        posterPath= intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0) ?: 0
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
        mAdapter= MovieSeatAdapter(DUMMY_SEATS, this)
        rvSeats.adapter = mAdapter
        rvSeats.layoutManager = GridLayoutManager(this, 14)
        requestSeatData()
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
            if (mPrice != 0) {
                startActivity(ChooseSnackActivity.newIntent(this, movieId, movieName, movieDuration, posterPath, movieDate, timeslotId, movieTime, cinemaId, cinemaName, seatRows, seatNames, mPrice))
            }
        }
    }

    private fun requestSeatData() {
        mCinemaModel.getSeatingPlan(
            date = movieDate,
            timeslotId = timeslotId,
            onSuccess = {
                val flattenList = it.flatten()
                mAdapter.setNewData(flattenList)
                seatList = flattenList
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    override fun onTapSeat(title: String, price: Int) {
        seatList.map {
            if (it.title.equals(title)) {
                it.isSelected = true
                ticketCount += 1

                if (!seatRows.contains("${it.symbol}")) {
                    if (seatRows.isEmpty()) {
                        seatRows += "${it.symbol}"
                    } else {
                        seatRows += ", ${it.symbol}"
                    }
                }

                if (seatNames.isEmpty()) {
                    seatNames += "${it.title}"
                } else {
                    seatNames += ", ${it.title}"
                }
                mPrice += price
                tvSeatCount.text = ticketCount.toString()
                tvSeatName.text = seatNames
                btnNext.text = "Buy tickets for $$mPrice"
            }
        }
        mAdapter.setNewData(seatList)
    }
}