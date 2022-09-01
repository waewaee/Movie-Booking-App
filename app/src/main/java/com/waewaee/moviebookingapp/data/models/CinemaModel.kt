package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.*
import com.waewaee.moviebookingapp.network.request.VoucherRequest
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

    fun getCinemaTimeslots(
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getSeatingPlan(
        date: String,
        timeslotId: Int,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getPaymentMethods(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun addNewCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<VisaCardVO>) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun checkout(
        voucherRequest: VoucherRequest,
        onSuccess: (CheckoutMovieInfoVO) -> Unit,
        onFailure: (String) -> Unit,
    )

}