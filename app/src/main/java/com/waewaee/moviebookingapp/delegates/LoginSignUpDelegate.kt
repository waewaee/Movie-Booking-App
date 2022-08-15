package com.waewaee.moviebookingapp.delegates

interface LoginSignUpDelegate {
    fun onTapLogin(userEmail: String, userPassword: String)
    fun onTapSignUp(userName: String, userEmail: String, userPassword: String, userPhone: String)
}