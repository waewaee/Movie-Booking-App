package com.waewaee.moviebookingapp.data.models

import android.content.Context
import com.waewaee.moviebookingapp.data.vos.ActorVO
import com.waewaee.moviebookingapp.network.dataagents.MovieDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitMovieDataAgentImpl
import com.waewaee.moviebookingapp.data.vos.MovieVO
import com.waewaee.moviebookingapp.data.vos.NOW_PLAYING
import com.waewaee.moviebookingapp.data.vos.UPCOMING
import com.waewaee.moviebookingapp.persistence.CinemaDatabase

object MovieModelImpl: MovieModel {

    // Data Agent
    val mMovieDataAgent: MovieDataAgent = RetrofitMovieDataAgentImpl

    // Database
    var mMovieDatabase: CinemaDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = CinemaDatabase.getDbInstance(context)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        var result = mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf()
        onSuccess(result)

        // Network
        mMovieDataAgent.getNowPlayingMovies(onSuccess = {

            it.forEach { movie -> movie.type = NOW_PLAYING }
            mMovieDatabase?.movieDao()?.insertMovies(it)

            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        var result = mMovieDatabase?.movieDao()?.getMoviesByType(type = UPCOMING) ?: listOf()
        onSuccess(result)

        // Network
        mMovieDataAgent.getComingSoonMovies(onSuccess = {

            it.forEach { movie -> movie.type = UPCOMING }
            mMovieDatabase?.movieDao()?.insertMovies(it)

            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let {
            onSuccess(it)
        }

        // Network
        mMovieDataAgent.getMovieDetails(movieId = movieId, onSuccess = {

            val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
            it.type = movieFromDatabase?.type

            mMovieDatabase?.movieDao()?.insertSingleMovie(it)

            onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mMovieDatabase?.actorDao()?.getActorsByMovieId(movieId = movieId.toInt()) ?: listOf())

        mMovieDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = {

                it.forEach { it.movieId = movieId.toInt() }
                mMovieDatabase?.actorDao()?.insertActors(it)

                onSuccess(it)
            },
            onFailure = onFailure
        )
    }
}