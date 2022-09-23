package com.waewaee.moviebookingapp.data.models

import android.content.Context
import com.waewaee.moviebookingapp.data.vos.*
import com.waewaee.moviebookingapp.network.dataagents.CinemaDataAgent
import com.waewaee.moviebookingapp.network.dataagents.RetrofitCinemaDataAgentImpl
import com.waewaee.moviebookingapp.network.request.VoucherRequest
import com.waewaee.moviebookingapp.persistence.CinemaDatabase

object CinemaModelImpl: CinemaModel {

    // Data Agent
    val mCinemaDataAgent: CinemaDataAgent = RetrofitCinemaDataAgentImpl
    private var userToken = ""

    // Database
    var mCinemaDatabase: CinemaDatabase? = null

    fun initDatabase(context: Context) {
        mCinemaDatabase = CinemaDatabase.getDbInstance(context)
    }

    fun checkLoginUser(): Boolean {
        val user = mCinemaDatabase?.userDao()?.getAllUsers()?.firstOrNull()
        user?.let {
            userToken = user.token ?: ""
        }
        return user != null
    }

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.getLoginWithEmail(email = email, password = password,
            onSuccess = { response ->
                val userVO = response.userVO
                userVO?.token = "Bearer ${response.token}"

                mCinemaDatabase?.userDao()?.insertUser(userVO ?: UserVO())

                val errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Not Found")
                onSuccess(errorVO)
        },
            onFailure = onFailure,
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
                val userVO = response.userVO
                userVO?.token = "Bearer ${response.token}"

                mCinemaDatabase?.userDao()?.insertUser(userVO ?: UserVO())

                userToken = "Bearer ${response.token}"
                val errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Not Found")

                onSuccess(errorVO)
        },
            onFailure = onFailure)
    }

    override fun getProfile(
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        onSuccess(mCinemaDatabase?.userDao()?.getAllUsers()?.firstOrNull() ?: UserVO())

         // Network
        mCinemaDataAgent.getProfile(authorization = userToken,
            onSuccess = { response ->
                val userVO = response.userVO
                userVO?.token = userToken
                onSuccess(userVO ?: UserVO())

                mCinemaDatabase?.userDao()?.insertUser(userVO ?: UserVO())
        }, onFailure = onFailure)
    }

    override fun logout(
        onSuccess: (ErrorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDatabase?.userDao()?.deleteAllUsers()

        mCinemaDataAgent.logout(authorization = userToken,
            onSuccess = { response ->
                val errorVO = ErrorVO(code = response.code ?: 404, message = response.message ?: "Unauthorized")
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
        onSuccess(mCinemaDatabase?.cinemaDao()?.getCinemaByDate(date = date) ?: listOf())

        mCinemaDataAgent.getCinemaTimeslots(authorization = userToken, date = date,
            onSuccess = { response ->
                val cinemaList = response.cinemaList

                cinemaList?.forEach {
                    it.date = date
                    it.key = "$date-${it.cinemaId}"
                }
                mCinemaDatabase?.cinemaDao()?.insertCinemas(cinemaList ?: listOf())

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
        onSuccess(mCinemaDatabase?.snackDao()?.getAllSnacks() ?: listOf())

        mCinemaDataAgent.getSnackList(authorization = userToken,
            onSuccess = { response ->
                val snackList: List<SnackVO> = response.snackList ?: listOf()

                mCinemaDatabase?.snackDao()?.insertSnacks(snackList)
                onSuccess(snackList)
        },
            onFailure = onFailure)
    }

    override fun getPaymentMethods(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(mCinemaDatabase?.paymentMethodDao()?.getAllPaymentMethods() ?: listOf())

        mCinemaDataAgent.getPaymentMethods(authorization = userToken,
            onSuccess = { response ->
                val methodList: List<PaymentMethodVO> = response.methodList ?: listOf()

                mCinemaDatabase?.paymentMethodDao()?.insertPaymentMethods(methodList)
                onSuccess(methodList)
            },
            onFailure = onFailure)
    }

    override fun addNewCard(
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<VisaCardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.addNewCard(authorization = userToken, cardNumber = cardNumber, cardHolder = cardHolder, expirationDate = expirationDate, cvc = cvc,
            onSuccess = { response ->
            val cardList: List<VisaCardVO> = response.card ?: listOf()
                onSuccess(cardList)
        },
            onFailure = onFailure)
    }

    override fun checkout(
        voucherRequest: VoucherRequest,
        onSuccess: (CheckoutMovieInfoVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaDataAgent.checkout(authorization = userToken, voucherRequest = voucherRequest,
            onSuccess = { response ->
                onSuccess(response.data ?: CheckoutMovieInfoVO())
            },
            onFailure = onFailure)
    }

}