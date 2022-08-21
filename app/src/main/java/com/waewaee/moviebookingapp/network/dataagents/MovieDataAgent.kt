package com.waewaee.moviebookingapp.network.dataagents

import com.waewaee.moviebookingapp.data.vos.ActorVO
import com.waewaee.themovieapp.data.vos.MovieVO

interface MovieDataAgent {

    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

}