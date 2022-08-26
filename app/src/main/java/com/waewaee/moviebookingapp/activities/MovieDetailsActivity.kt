package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.CastAdapter
import com.waewaee.moviebookingapp.adapters.GenreChipAdapter
import com.waewaee.moviebookingapp.data.models.MovieModel
import com.waewaee.moviebookingapp.data.models.MovieModelImpl
import com.waewaee.moviebookingapp.utils.MOVIE_IMAGE_BASE_URL
import com.waewaee.themovieapp.data.vos.MovieVO
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    private val mMovieModel: MovieModel = MovieModelImpl

    lateinit var castAdapter: CastAdapter
    lateinit var genreChipAdapter: GenreChipAdapter
    lateinit var movieName: String
    lateinit var movieDuration: String

    private var movieId: Int = 0

    companion object {
        private val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent =  Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
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

        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0) ?: 0
        requestDetailData(movieId)
        requestCastData(movieId)
    }

    private fun requestCastData(movieId: Int) {
        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                castAdapter.setNewData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun requestDetailData(movieId: Int) {
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                bindDetailData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
    }

    private fun bindDetailData(movie: MovieVO) {
        Glide.with(this)
            .load("$MOVIE_IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieImage)

        tvMovieName.text = movie.title ?: ""
        movieName = movie.title ?: ""

        tvMovieDuration.text = movie.getRuntimeHourMinute()
        movieDuration = movie.getRuntimeHourMinute()

        ratingBar.rating = movie.getRatingBasedOnFiveStars()
        movie.voteAverage?.let {
            tvMovieRating.text = "iMDb $it"
        }
        tvPlotSummary.text = movie.overView ?: ""

        genreChipAdapter.setNewData(movie.genres ?: listOf())
    }

    private fun setUpListeners() {
        btnGetTicket.setOnClickListener {
            startActivity(BookTicketActivity.newIntent(this, movieId, movieName, movieDuration))
        }

        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpCastRecyclerView() {
        castAdapter = CastAdapter()
        rvCasts.adapter = castAdapter
        rvCasts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpGenreRecyclerView() {
        genreChipAdapter = GenreChipAdapter()
        rvGenre.adapter = genreChipAdapter
        rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}