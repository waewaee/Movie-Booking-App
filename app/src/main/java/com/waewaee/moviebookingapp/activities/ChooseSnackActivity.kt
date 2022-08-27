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
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.delegates.SnackDelegate
import kotlinx.android.synthetic.main.activity_choose_snack.*

class ChooseSnackActivity : AppCompatActivity(), SnackDelegate {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var seatNames: String
    lateinit var snackList: List<SnackVO>
    lateinit var snackAdapter: SnackAdapter
    lateinit var paymentMethodAdapter: PaymentMethodAdapter

//    private var seatPrice = 0
    private var subTotal: Int = 0
    private val mCinemaModel: CinemaModel = CinemaModelImpl

    companion object {
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_SEAT_NAME = "EXTRA_SEAT_NAME"
        private val EXTRA_SEAT_PRICE = "EXTRA_SEAT_PRICE"

        fun newIntent(context: Context, movieName: String, movieDuration: String, movieDate: String, movieTime: String, cinemaName: String, seatNames: String, seatPrice: Int): Intent {
            val intent = Intent(context, ChooseSnackActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
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

        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        seatNames = intent?.getStringExtra(EXTRA_SEAT_NAME) ?: ""
        subTotal = intent?.getIntExtra(EXTRA_SEAT_PRICE, 0) ?: 0

        tvSubTotal.text = "Sub Total : $subTotal"
        btnPay.text = "Pay $$subTotal"
    }

    private fun requestSnackData() {
        mCinemaModel.getSnackList(
            onSuccess = {
                snackAdapter.setNewData(it)
                snackList = it
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListeners() {
        btnPay.setOnClickListener {
//            startActivity(PaymentActivity.newIntent(this))
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpPaymentMethodRecyclerView() {
        paymentMethodAdapter = PaymentMethodAdapter()
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


}