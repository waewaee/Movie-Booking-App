package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CastAdapter
import com.waewaee.moviebookingapp.adapters.GenreChipAdapter
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setUpGenreRecyclerView()
        setUpCastRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        btnGetTicket.setOnClickListener {
            startActivity(BookTicketActivity.newIntent(this))
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpCastRecyclerView() {
        val castAdapter = CastAdapter()
        rvCasts.adapter = castAdapter
        rvCasts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpGenreRecyclerView() {
        val genreChipAdapter = GenreChipAdapter()
        rvGenre.adapter = genreChipAdapter
        rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}