package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.network.dataagents.MovieDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitMovieDataAgentImpl
import com.waewaee.themovieapp.data.vos.MovieVO

object MovieModelImpl: MovieModel {

    val mMovieDataAgent: MovieDataAgent = RetrofitMovieDataAgentImpl

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getComingSoonMovies(onSuccess = onSuccess, onFailure = onFailure)
    }
}