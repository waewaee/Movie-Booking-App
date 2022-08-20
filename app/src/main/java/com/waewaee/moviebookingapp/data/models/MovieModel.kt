package com.waewaee.moviebookingapp.data.models

import com.waewaee.themovieapp.data.vos.MovieVO

interface MovieModel {
    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>)  -> Unit,
        onFailure : (String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}