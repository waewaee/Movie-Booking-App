package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.ErrorVO
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
        mCinemaDataAgent.getLoginWithEmail(email = email, password = password, onSuccess = onSuccess, onFailure = onFailure, setToken = {
            userToken = it
        })
    }

    override fun getToken() : String {
        return userToken
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
            userToken = response.token ?: ""
            var errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Not Found")
            onSuccess(errorVO)
        }, onFailure = onFailure)
    }
}