package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.MovieSeatAdapter
import com.waewaee.moviebookingapp.dummy.DUMMY_SEATS
import kotlinx.android.synthetic.main.activity_seating_plan.*

class SeatingPlanActivity : AppCompatActivity() {

    private val mAdapter= MovieSeatAdapter()
    
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SeatingPlanActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seating_plan)

        setUpSeatRecyclerView()
        setUpListeners()
    }

    private fun setUpSeatRecyclerView() {
        rvSeats.adapter = mAdapter
        rvSeats.layoutManager = GridLayoutManager(this, 10)

        mAdapter.setNewData(DUMMY_SEATS)
    }

    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

        btnNext.setOnClickListener {
            startActivity(ChooseSnackActivity.newIntent(this))
        }
    }
}