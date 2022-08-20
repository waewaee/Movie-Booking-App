package com.waewaee.moviebookingapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.waewaee.moviebookingapp.R
import com.waewaee.moviebookingapp.adapters.MovieAdapter
import com.waewaee.moviebookingapp.data.models.CinemaModel
import com.waewaee.moviebookingapp.data.models.CinemaModelImpl
import com.waewaee.moviebookingapp.data.models.MovieModel
import com.waewaee.moviebookingapp.data.models.MovieModelImpl
import com.waewaee.moviebookingapp.delegates.MovieViewHolderDelegate
import com.waewaee.moviebookingapp.utils.CINEMA_BASE_URL
import com.waewaee.moviebookingapp.utils.IMAGE_BASE_URL
import com.waewaee.moviebookingapp.view.pods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity(), MovieViewHolderDelegate {

    lateinit var mNowShowingMovieListViewPod: MovieListViewPod
    lateinit var mComingSoonMovieListViewPod: MovieListViewPod
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var mMovieAdapter: MovieAdapter

    private val mCinemaModel: CinemaModel = CinemaModelImpl
    private val mMovieModel: MovieModel = MovieModelImpl

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
        setUpDrawer()
        setUpListeners()
        requestData()

//        Snackbar.make(window.decorView, mCinemaModel.getToken(), Snackbar.LENGTH_SHORT).show()
    }

    private fun requestData() {
        mCinemaModel.getProfile(
            onSuccess = {
                tvProfileName.text = "Hi ${it.name}"
                tvProfileNameOnMenu.text = it.name
                tvProfileEmailOnMenu.text = it.email

                Glide.with(this)
                    .load("$CINEMA_BASE_URL${it.profileImage}")
                    .into(ivProfile)

                Glide.with(this)
                    .load("$CINEMA_BASE_URL${it.profileImage}")
                    .into(ivProfileOnMenu)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            })

        mMovieModel.getNowPlayingMovies(
            onSuccess = {
                mNowShowingMovieListViewPod.setData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )

        mMovieModel.getComingSoonMovies(
            onSuccess = {
                mComingSoonMovieListViewPod.setData(it)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )

    }

    private fun setUpListeners() {
        ivProfileOnMenu.setOnClickListener {
            Snackbar.make(window.decorView, "Profile picture tapped", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setUpDrawer() {
        actionBarDrawerToggle = ActionBarDrawerToggle(this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer )

        actionBarDrawerToggle.let {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }

        tvLogOutOnMenu.setOnClickListener {
            drawerLayout.close()

            mCinemaModel.logout(
                onSuccess = {
                    if (it.code == 200) {
                        finishAffinity()
                        startActivity(LoginActivity.newIntent(this))
                    } else {
                        Snackbar.make(window.decorView, "${it.message}", Snackbar.LENGTH_SHORT).show()
                    }
                },
                onFailure = {
                    Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
                }
            )
        }
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
        mComingSoonMovieListViewPod.setUpMovieListViewPod(this, getString(R.string.new_title_movie_list_view_pod))


    }

    override fun onTapMovie() {
//        Snackbar.make(window.decorView, "Tapped movie", Snackbar.LENGTH_SHORT).show()
        startActivity(MovieDetailsActivity.newIntent(this))
    }
}