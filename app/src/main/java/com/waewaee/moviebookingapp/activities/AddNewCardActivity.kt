package com.waewaee.moviebookingapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import kotlinx.android.synthetic.main.activity_add_new_card.*
import java.io.Serializable

class AddNewCardActivity : AppCompatActivity(), Serializable {

    lateinit var cardObj: VisaCardVO

    private val mCinemaModel: CinemaModel = CinemaModelImpl

    companion object{

        private val EXTRA_NEW_CARD = "EXTRA_NEW_CARD"

        fun  newIntent(context: Context): Intent {
            return Intent(context, AddNewCardActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        setUpListeners()
    }

    private fun requestAddCardData() {
        mCinemaModel.addNewCard(
            cardNumber = etCardNumber.text.toString(),
            cardHolder = etCardHolderName.text.toString(),
            expirationDate = etExpirationDate.text.toString(),
            cvc = etCVC.text.toString().toInt(),
            onSuccess = {
                cardObj = it.lastOrNull() ?: VisaCardVO()
                val intent = Intent()
                    .putExtra(EXTRA_NEW_CARD, cardObj as Serializable)
                setResult(Activity.RESULT_OK, intent)
                finish()
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun setUpListeners() {
        btnConfirm.setOnClickListener {
            if (checkFields()) {
                requestAddCardData()
            }
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun checkFields(): Boolean {
        val cardNumberField: Boolean = etCardNumber.text.toString().isNotEmpty()
        val cardHolderField: Boolean = etCardHolderName.text.toString().isNotEmpty()
        val expirationDateField: Boolean = etExpirationDate.text.toString().isNotEmpty()
        val cvcField: Boolean = etCVC.text.toString().isNotEmpty()

        return cardNumberField && cardHolderField && expirationDateField && cvcField

    }
}