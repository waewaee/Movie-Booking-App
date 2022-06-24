package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.delegates.MovieViewHolderDelegate
import com.waewaee.moviebookingapp.view.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieViewHolderDelegate {

    lateinit var mNowShowingMovieListViewPod: MovieListViewPod
    lateinit var mComingSoonMovieListViewPod: MovieListViewPod

    companion object {
        fun newIntent(context: Context): Intent{
            return Intent(context, MovieListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setUpToolbar()
        setUpViewPods()

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_small_black)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
        return true
    }

    private fun setUpViewPods() {
        mNowShowingMovieListViewPod = vpNowShowingMovieList as MovieListViewPod
        mNowShowingMovieListViewPod.setUpMovieListViewPod(this)

        mComingSoonMovieListViewPod = vpComingSoonMovieList as MovieListViewPod
        mComingSoonMovieListViewPod.setUpMovieListViewPod(this, "Coming Soon")
    }

    override fun onTapMovie() {
//        Snackbar.make(window.decorView, "Tapped movie", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this))
    }
}