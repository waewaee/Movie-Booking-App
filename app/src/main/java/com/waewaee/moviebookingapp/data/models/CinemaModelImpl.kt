package com.waewaee.moviebookingapp.data.models

import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.network.dataagents.CinemaDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitCinemaDataAgentImpl

object CinemaModelImpl: CinemaModel {

    val mCinemaDataAgent: CinemaDataAgent = RetrofitCinemaDataAgentImpl
    private var token = ""

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getLoginWithEmail(email = email, password = password, onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getSignUpWithEmail(name = name, email = email, password = password, phone = phone, onSuccess = onSuccess, onFailure = onFailure)
    }
}