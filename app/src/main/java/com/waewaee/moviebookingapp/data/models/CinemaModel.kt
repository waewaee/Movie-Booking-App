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

    fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getProfile(
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun logout(
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit,
    )

}