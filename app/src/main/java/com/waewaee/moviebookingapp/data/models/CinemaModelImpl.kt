package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.CardVO
import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.data.vos.UserVO
import com.waewaee.moviebookingapp.network.dataagents.CinemaDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitCinemaDataAgentImpl
import com.waewaee.themovieapp.data.vos.MovieVO

object CinemaModelImpl: CinemaModel {

    val mCinemaDataAgent: CinemaDataAgent = RetrofitCinemaDataAgentImpl
    private var userToken = ""

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getLoginWithEmail(email = email, password = password, onSuccess = onSuccess, onFailure = onFailure, setToken = {
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
        mCinemaDataAgent.getSignUpWithEmail(name = name, email = email, password = password, phone = phone, onSuccess = { response ->
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
        mCinemaDataAgent.getProfile(authorization = userToken, onSuccess = { response ->
            var userVO = response.userVO
            onSuccess(userVO ?: UserVO())
        }, onFailure = onFailure)
    }

    override fun logout(
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.logout(authorization = userToken, onSuccess = { response ->
            var errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Unauthorized")
            userToken = ""
            onSuccess(errorVO)
        },
        onFailure = onFailure)
    }

}