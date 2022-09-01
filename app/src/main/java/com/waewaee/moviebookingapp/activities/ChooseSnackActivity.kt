package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.PaymentMethodAdapter
import com.waewaee.moviebookingapp.adapters.SnackAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.vos.PaymentMethodVO
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.delegates.PaymentMethodDelegate
import com.waewaee.moviebookingapp.delegates.SnackDelegate
import kotlinx.android.synthetic.main.activity_choose_snack.*

class ChooseSnackActivity : AppCompatActivity(), SnackDelegate, PaymentMethodDelegate {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var seatNames: String
    lateinit var seatRows: String
    lateinit var paymentMethod: String
    lateinit var snackList: List<SnackVO>
    lateinit var methodList: List<PaymentMethodVO>
    lateinit var snackAdapter: SnackAdapter
    lateinit var paymentMethodAdapter: PaymentMethodAdapter
    lateinit var posterPath: String

    private var movieId: Int = 0
    private var cinemaId: Int = 0
    private var timeslotId: Int = 0
    private var subTotal: Int = 0
    private var selectedSnackList = mutableListOf<SnackVO>()
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
        private val EXTRA_SEAT_PRICE = "EXTRA_SEAT_PRICE"

        fun newIntent(context: Context, movieId: Int, movieName: String, movieDuration: String, posterPath: String, movieDate: String, timeslotId: Int, movieTime: String, cinemaId: Int, cinemaName: String, seatRows: String, seatNames: String, seatPrice: Int): Intent {
            val intent = Intent(context, ChooseSnackActivity::class.java)

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
            intent.putExtra(EXTRA_SEAT_PRICE, seatPrice)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_snack)

        setUpSnackRecyclerView()
        setUpPaymentMethodRecyclerView()
        setUpListeners()
        requestSnackData()
        requestPaymentMethodData()

        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        posterPath= intent?.getStringExtra(EXTRA_POSTER_PATH) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        timeslotId = intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0) ?: 0
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0) ?: 0
        seatRows = intent?.getStringExtra(EXTRA_SEAT_ROW) ?: ""
        seatNames = intent?.getStringExtra(EXTRA_SEAT_NAME) ?: ""
        subTotal = intent?.getIntExtra(EXTRA_SEAT_PRICE, 0) ?: 0

        tvSubTotal.text = "Sub Total : $subTotal"
        btnPay.text = "Pay $$subTotal"
    }

    private fun requestPaymentMethodData() {
        mCinemaModel.getPaymentMethods(
            onSuccess = {
                methodList= it
                paymentMethodAdapter.setNewData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun requestSnackData() {
        mCinemaModel.getSnackList(
            onSuccess = {
                snackList = it
                snackAdapter.setNewData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListeners() {
        btnPay.setOnClickListener {
            if (paymentMethod.isNotEmpty()) {
                snackList.map {
                    if (it.snackCount > 0) {
                        selectedSnackList.add(it)
                    }
                }
                startActivity(PaymentActivity.newIntent(this, movieId, movieName, movieDuration, movieDate, posterPath, timeslotId, movieTime, cinemaId, cinemaName, seatRows, seatNames, selectedSnackList, subTotal, paymentMethod))
            }
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpPaymentMethodRecyclerView() {
        paymentMethodAdapter = PaymentMethodAdapter(this)
        rvPaymentMethods.adapter = paymentMethodAdapter
        rvPaymentMethods.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpSnackRecyclerView() {
        snackAdapter = SnackAdapter(this)
        rvSnacks.adapter = snackAdapter
        rvSnacks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onTapPlus(id: Int, price: Int) {
        snackList.map {
            if (it.id == id) {
                it.snackCount += 1
                it.totalPrice += price
                subTotal += price
                tvSubTotal.text = "Sub Total : $$subTotal"
                btnPay.text = "Pay $$subTotal"
            }
        }
        snackAdapter.setNewData(snackList)
    }

    override fun onTapMinus(id: Int, price: Int) {
        snackList.map {
            if (it.id == id) {
                if (it.snackCount > 0) {
                    it.snackCount -= 1
                    it.totalPrice -= price
                    subTotal -= price
                }
                tvSubTotal.text = "Sub Total : $$subTotal"
                btnPay.text = "Pay $$subTotal"
            }
        }
        snackAdapter.setNewData(snackList)
    }

    override fun onTapPaymentMethod(id: Int) {
        methodList.map {
            it.isSelected = it.id == id
            paymentMethod = it.paymentName ?: ""
        }
        paymentMethodAdapter.setNewData(methodList)
    }


}