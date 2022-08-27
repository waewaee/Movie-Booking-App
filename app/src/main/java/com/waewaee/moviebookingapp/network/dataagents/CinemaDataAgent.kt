package com.waewaee.moviebookingapp.network.dataagents

import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.network.responses.LoginResponse
import com.waewaee.moviebookingapp.network.responses.SeatingPlanResponse
import com.waewaee.moviebookingapp.network.responses.TimeslotsResponse

interface CinemaDataAgent {

    fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
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

}