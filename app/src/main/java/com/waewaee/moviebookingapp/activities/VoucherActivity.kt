package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.waewaee.moviebookingapp.R
import kotlinx.android.synthetic.main.activity_voucher.*

class VoucherActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, VoucherActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voucher)

        setUpListeners()
    }

    private fun setUpListeners() {
        btnClose.setOnClickListener {
            startActivity(MovieListActivity.newIntent(this))
        }
    }
}