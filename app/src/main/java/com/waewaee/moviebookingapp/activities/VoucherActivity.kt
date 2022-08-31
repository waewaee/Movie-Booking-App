package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.vos.CheckoutMovieInfoVO
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.network.request.SnackCriteria
import com.waewaee.moviebookingapp.network.request.VoucherRequest
import com.waewaee.moviebookingapp.utils.MOVIE_BASE_URL
import kotlinx.android.synthetic.main.activity_voucher.*
import java.io.Serializable

class VoucherActivity : AppCompatActivity() {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var seatNames: String
    lateinit var seatRows: String
    lateinit var paymentMethod: String
    lateinit var snackList: List<SnackVO>
    lateinit var posterPath: String

    private var movieId: Int = 0
    private var cinemaId: Int = 0
    private var timeslotId: Int = 0
    private var totalPrice: Int = 0
    private var selectedCardId = 0
    private var snackCriteriaList = mutableListOf<SnackCriteria>()
    private val mCinemaModel: CinemaModel = CinemaModelImpl

    companion object {


        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_POSTER_PATH = "EXTRA_POSTER_PATH"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private val EXTRA_SEAT_ROW = "EXTRA_SEAT_ROW"
        private val EXTRA_SEAT_NAME = "EXTRA_SEAT_NAME"
        private val EXTRA_SNACK_LIST = "EXTRA_SNACK_LIST"
        private val EXTRA_SUB_TOTAL = "EXTRA_SUB_TOTAL"
        private val EXTRA_PAYMENT_METHOD = "EXTRA_PAYMENT_METHOD"
        private val EXTRA_SELECTED_CARD_ID = "EXTRA_SELECTED_CARD_ID"

        fun newIntent(context: Context, movieId: Int, movieName: String, movieDuration: String, posterPath: String, movieDate: String, timeslotId: Int, movieTime: String, cinemaId: Int, cinemaName: String, seatRows: String, seatNames: String, snackList: List<SnackVO>, subTotal: Int, paymentMethod: String, selectedCardId: Int): Intent {

            val intent = Intent(context, VoucherActivity::class.java)


            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_POSTER_PATH, posterPath)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_SEAT_ROW, seatRows)
            intent.putExtra(EXTRA_SEAT_NAME, seatNames)
            intent.putExtra(EXTRA_SNACK_LIST, snackList as Serializable)
            intent.putExtra(EXTRA_SUB_TOTAL, subTotal)
            intent.putExtra(EXTRA_PAYMENT_METHOD, paymentMethod)
            intent.putExtra(EXTRA_SELECTED_CARD_ID, selectedCardId)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        setUpListeners()


        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        posterPath= intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        timeslotId = intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0) ?: 0
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0) ?: 0
        seatRows = intent?.getStringExtra(EXTRA_SEAT_ROW) ?: ""
        seatNames = intent?.getStringExtra(EXTRA_SEAT_NAME) ?: ""
        snackList = intent?.getSerializableExtra(EXTRA_SNACK_LIST) as List<SnackVO>
        totalPrice = intent?.getIntExtra(EXTRA_SUB_TOTAL, 0) ?: 0
        paymentMethod = intent?.getStringExtra(EXTRA_PAYMENT_METHOD) ?: ""
        selectedCardId = intent?.getIntExtra(EXTRA_SELECTED_CARD_ID, 0) ?: 0

        snackList.map {
            snackCriteriaList.add(SnackCriteria(it.id ?: 0, it.snackCount))
        }

        val voucherRequest = VoucherRequest(
            booking_date = movieDate,
            card_id = selectedCardId,
            cinema_day_timeslot_id = timeslotId,
            cinema_id = cinemaId,
            movie_id = movieId,
            row = seatRows.dropLast(1),
            seat_number = seatNames.dropLast(1),
            snacks = snackCriteriaList,
            total_price = totalPrice
        )

        val requestString: String = Gson().toJson(voucherRequest)
        requestCheckOutData(requestString)
    }

    private fun requestCheckOutData(request: String) {
        mCinemaModel.checkout(
            voucherRequest = request,
            onSuccess = {
                bindData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun bindData(movieInfo: CheckoutMovieInfoVO) {
        Glide.with(this)
            .load("$MOVIE_BASE_URL$posterPath")
            .into(ivMovieImage)

        tvMovieName.text = movieName
        tvMovieDuration.text = movieDuration
        tvMovieDimension.text = cinemaName

        tvBookingNo.text = movieInfo.bookingNo
        tvShowtimeDate.text = "$movieTime - ${movieInfo.bookingDate}"
        tvTheater.text = cinemaName
        tvRow.text = movieInfo.seatRows
        tvSeat.text = movieInfo.seatNames
        tvPrice.text = movieInfo.totalPrice.toString()
    }

    private fun setUpListeners() {
        btnClose.setOnClickListener {
            finishActivity(0)
            startActivity(MovieListActivity.newIntent(this))
        }
    }
}