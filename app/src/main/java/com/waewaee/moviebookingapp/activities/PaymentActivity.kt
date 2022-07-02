package com.waewaee.moviebookingapp.activities

import alirezat775.carouselview.sample.VisaCardVO
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CarouselAdapter
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PaymentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnAddNewCard.setOnClickListener {
            startActivity(AddNewCardActivity.newIntent(this))
        }

        btnPay.setOnClickListener {
            startActivity(VoucherActivity.newIntent(this))
        }

        val adapter = CarouselAdapter()
        val carousel = alirezat775.lib.carouselview.Carousel(this, cardCarousel, adapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)

        carousel.add(VisaCardVO(1))
        carousel.add(VisaCardVO(2))
        carousel.add(VisaCardVO(3))
        carousel.add(VisaCardVO(4))
        carousel.add(VisaCardVO(5))

        carousel.setCurrentPosition(3)
    }
}