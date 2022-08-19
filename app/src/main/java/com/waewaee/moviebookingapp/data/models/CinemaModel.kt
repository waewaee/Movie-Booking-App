package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.data.vos.UserVO
import com.waewaee.themovieapp.data.vos.MovieVO

interface CinemaModel {

    fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getToken() : String

    fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getProfile(
        authorization: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getNowPlayingMovies(
        onSuccess : (List<MovieVO>)  -> Unit,
        onFailure : (String) -> Unit
    )
}