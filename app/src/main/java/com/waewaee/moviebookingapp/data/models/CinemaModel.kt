package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.ErrorVO

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
}