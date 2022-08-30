package com.waewaee.moviebookingapp.activities

import alirezat775.lib.carouselview.Carousel
import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import alirezat775.lib.carouselview.CarouselView
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CarouselAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.vos.SnackVO
import com.waewaee.moviebookingapp.delegates.VisaCardDelegate
import com.waewaee.moviebookingapp.utils.CINEMA_BASE_URL
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.activity_payment.*
import java.io.Serializable

class PaymentActivity : AppCompatActivity(), VisaCardDelegate, Serializable {

    lateinit var movieDate: String
    lateinit var movieTime: String
    lateinit var movieName: String
    lateinit var movieDuration: String
    lateinit var cinemaName: String
    lateinit var seatNames: String
    lateinit var seatRows: String
    lateinit var paymentMethod: String
    lateinit var cardObj: VisaCardVO
    lateinit var cardList: List<VisaCardVO>
    lateinit var carousel: Carousel
    lateinit var carouselAdapter: CarouselAdapter
    lateinit var snackList: List<SnackVO>

    private var movieId: Int = 0
    private var cinemaId: Int = 0
    private var subTotal: Int = 0
    private var selectedCardId = 0
    private val mCinemaModel: CinemaModel = CinemaModelImpl

    private val CARD_REQUEST_CODE = 100

    companion object {
        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private val EXTRA_MOVIE_DURATION = "EXTRA_MOVIE_DURATION"
        private val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private val EXTRA_MOVIE_TIME = "EXTRA_MOVIE_TIME"
        private val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private val EXTRA_SEAT_ROW = "EXTRA_SEAT_ROW"
        private val EXTRA_SEAT_NAME = "EXTRA_SEAT_NAME"
        private val EXTRA_SNACK_LIST = "EXTRA_SNACK_LIST"
        private val EXTRA_SUB_TOTAL = "EXTRA_SUB_TOTAL"
        private val EXTRA_PAYMENT_METHOD = "EXTRA_PAYMENT_METHOD"
        private val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun newIntent(context: Context, movieId: Int, movieName: String, movieDuration: String, movieDate: String, movieTime: String, cinemaId: Int, cinemaName: String, seatRows: String, seatNames: String, snackList: List<SnackVO>, subTotal: Int, paymentMethod: String): Intent {
            val intent = Intent(context, PaymentActivity::class.java)

            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_DURATION, movieDuration)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_MOVIE_TIME, movieTime)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_SEAT_ROW, seatRows)
            intent.putExtra(EXTRA_SEAT_NAME, seatNames)
            intent.putExtra(EXTRA_SNACK_LIST, snackList as Serializable)
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


        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME) ?: ""
        movieDuration = intent?.getStringExtra(EXTRA_MOVIE_DURATION) ?: ""
        movieDate = intent?.getStringExtra(EXTRA_MOVIE_DATE) ?: ""
        movieTime = intent?.getStringExtra(EXTRA_MOVIE_TIME) ?: ""
        cinemaName = intent?.getStringExtra(EXTRA_CINEMA_NAME) ?: ""
        cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0) ?: 0
        seatRows = intent?.getStringExtra(EXTRA_SEAT_ROW) ?: ""
        seatNames = intent?.getStringExtra(EXTRA_SEAT_NAME) ?: ""
        snackList = intent?.getSerializableExtra(EXTRA_SNACK_LIST) as List<SnackVO>
        subTotal = intent?.getIntExtra(EXTRA_SUB_TOTAL, 0) ?: 0
        paymentMethod = intent?.getStringExtra(EXTRA_PAYMENT_METHOD) ?: ""

        tvTotal.text = "$ $subTotal"
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnAddNewCard.setOnClickListener {
            startActivity(AddNewCardActivity.newIntent(this))
        }

        btnPay.setOnClickListener {
            selectedCardId.let {
                startActivity(VoucherActivity.newIntent(this, movieId, movieName, movieDuration, movieDate, movieTime, cinemaId, cinemaName, seatRows, seatNames, snackList, subTotal, paymentMethod, selectedCardId))
            }
        }
    }

    private fun setUpCarousel() {
         carouselAdapter = CarouselAdapter(this)
        carousel = Carousel(this, cardCarousel, carouselAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)

//        carousel.add(VisaCardVO(1))
//        carousel.add(VisaCardVO(2))
//        carousel.add(VisaCardVO(3))
//        carousel.add(VisaCardVO(4))
//        carousel.add(VisaCardVO(5))
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == CARD_REQUEST_CODE && resultCode == Activity.RESULT_OK && data?.data != null) {
//            cardObj =data.getSerializableExtra(EXTRA_NEW_CARD) as VisaCardVO
//        }
//    }

    override fun onResume() {
        super.onResume()
        mCinemaModel.getProfile(
            onSuccess = {
                cardList = it.cards ?: listOf()
                carousel.addAll(it.cards?.reversed()?.toMutableList() ?: mutableListOf())
//                carousel.setCurrentPosition(3)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            })
    }

    override fun onTapCard(cardId: Int) {
        cardList.map {
            it.isSelected = it.id == cardId
        }
        carouselAdapter.setNewData(cardList)
    }
}