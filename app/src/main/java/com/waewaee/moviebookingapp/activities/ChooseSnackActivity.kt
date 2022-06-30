package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.PaymentMethodAdapter
import com.waewaee.moviebookingapp.adapters.SnackAdapter
import kotlinx.android.synthetic.main.activity_choose_snack.*

class ChooseSnackActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChooseSnackActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_snack)

        val snackAdapter = SnackAdapter()
        rvSnacks.adapter = snackAdapter
        rvSnacks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val paymentMethodAdapter = PaymentMethodAdapter()
        rvPaymentMethods.adapter = paymentMethodAdapter
        rvPaymentMethods.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}