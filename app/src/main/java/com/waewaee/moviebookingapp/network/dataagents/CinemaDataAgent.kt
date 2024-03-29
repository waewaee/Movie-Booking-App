package com.waewaee.moviebookingapp.network.dataagents

import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import com.waewaee.moviebookingapp.network.request.VoucherRequest
import com.waewaee.moviebookingapp.network.responses.*

interface CinemaDataAgent {

    fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit,
        setToken: (String) -> Unit,
    )

    fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getProfile(
        authorization: String,
        onSuccess: (LoginResponse)-> Unit,
        onFailure: (String) -> Unit,
    )

    fun logout(
    authorization: String,
    onSuccess: (LoginResponse)-> Unit,
    onFailure: (String) -> Unit,
    )

    fun getCinemaTimeslots(
        date: String,
        authorization: String,
        onSuccess: (TimeslotsResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSeatingPlan(
        timeslotId: Int,
        date: String,
        authorization: String,
        onSuccess: (SeatingPlanResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getSnackList(
        authorization: String,
        onSuccess: (SnackListResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getPaymentMethods(
        authorization: String,
        onSuccess: (PaymentMethodResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

    fun addNewCard(
        authorization: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (AddNewCardResponse) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkout(
        authorization: String,
        voucherRequest: VoucherRequest,
        onSuccess: (CheckoutResponse) -> Unit,
        onFailure: (String) -> Unit,
    )

}