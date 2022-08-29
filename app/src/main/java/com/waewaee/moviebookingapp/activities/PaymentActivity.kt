package com.waewaee.moviebookingapp.activities

import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import alirezat775.lib.carouselview.CarouselView
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CarouselAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var seatNames: String
    lateinit var paymentMethod: String
    lateinit var cardObj: VisaCardVO

    private var subTotal: Int = 0
    private val mCinemaModel: CinemaModel = CinemaModelImpl

    private val CARD_REQUEST_CODE = 100

    companion object {
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_SEAT_NAME = "EXTRA_SEAT_NAME"
        private val EXTRA_SUB_TOTAL = "EXTRA_SUB_TOTAL"
        private val EXTRA_PAYMENT_METHOD = "EXTRA_PAYMENT_METHOD"
        private val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun newIntent(context: Context, movieName: String, movieDuration: String, movieDate: String, movieTime: String, cinemaName: String, seatNames: String, subTotal: Int, paymentMethod: String): Intent {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_SEAT_NAME, seatNames)
            intent.putExtra(EXTRA_SUB_TOTAL, subTotal)
            intent.putExtra(EXTRA_PAYMENT_METHOD, paymentMethod)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        setUpCarousel()
        setUpListeners()

        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        seatNames = intent?.getStringExtra(EXTRA_SEAT_NAME) ?: ""
        subTotal = intent?.getIntExtra(EXTRA_SUB_TOTAL, 0) ?: 0
        paymentMethod = intent?.getStringExtra(EXTRA_PAYMENT_METHOD) ?: ""

        tvTotal.text = "$ $subTotal"
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnAddNewCard.setOnClickListener {
            startActivityForResult(AddNewCardActivity.newIntent(this), 100)
        }

        btnPay.setOnClickListener {
//            startActivity(VoucherActivity.newIntent(this))
        }
    }

    private fun setUpCarousel() {
        val adapter = CarouselAdapter()
        val carousel = alirezat775.lib.carouselview.Carousel(this, cardCarousel, adapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)

//        carousel.add(VisaCardVO(1))
//        carousel.add(VisaCardVO(2))
//        carousel.add(VisaCardVO(3))
//        carousel.add(VisaCardVO(4))
//        carousel.add(VisaCardVO(5))

        carousel.setCurrentPosition(3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CARD_REQUEST_CODE && resultCode == Activity.RESULT_OK && data?.data != null) {
            cardObj =data.getSerializableExtra(EXTRA_NEW_CARD) as VisaCardVO
        }
    }
}