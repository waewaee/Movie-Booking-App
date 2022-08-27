package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.*
import com.waewaee.moviebookingapp.network.dataagents.CinemaDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitCinemaDataAgentImpl

object CinemaModelImpl: CinemaModel {

    val mCinemaDataAgent: CinemaDataAgent = RetrofitCinemaDataAgentImpl
    private var userToken = ""

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getLoginWithEmail(email = email, password = password, onSuccess = onSuccess, onFailure = onFailure,
            setToken = {
            userToken = "Bearer $it"
        })
    }


    override fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getSignUpWithEmail(name = name, email = email, password = password, phone = phone,
            onSuccess = { response ->
                userToken = "Bearer ${response.token}"
                var errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Not Found")
                onSuccess(errorVO)
        },
            onFailure = onFailure)
    }

    override fun getProfile(
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getProfile(authorization = userToken,
            onSuccess = { response ->
                var userVO = response.userVO
                onSuccess(userVO ?: UserVO())
        }, onFailure = onFailure)
    }

    override fun logout(
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.logout(authorization = userToken,
            onSuccess = { response ->
                var errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Unauthorized")
                userToken = ""
                onSuccess(errorVO)
        },
        onFailure = onFailure)
    }

    override fun getCinemaTimeslots(
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getCinemaTimeslots(authorization = userToken, date = date,
            onSuccess = { response ->
                val cinemaList = response.cinemaList
                onSuccess(cinemaList ?: listOf())
        },
        onFailure = onFailure)
    }

    override fun getSeatingPlan(
        date: String,
        timeslotId: Int,
        onSuccess: (List<List<MovieSeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getSeatingPlan(authorization = userToken, date = date, timeslotId = timeslotId,
            onSuccess = { response ->
                val rowList: List<List<MovieSeatVO>> = response.data ?: listOf()
                onSuccess(rowList)
        },
            onFailure = onFailure)
    }

    override fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getSnackList(authorization = userToken,
            onSuccess = { response ->
                val snackList: List<SnackVO> = response.snackList ?: listOf()
                onSuccess(snackList)
        },
            onFailure = onFailure)
    }

    override fun getPaymentMethods(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getPaymentMethods(authorization = userToken,
            onSuccess = { response ->
                val methodList: List<PaymentMethodVO> = response.methodList ?: listOf()
                onSuccess(methodList)
            },
            onFailure = onFailure)
    }

}