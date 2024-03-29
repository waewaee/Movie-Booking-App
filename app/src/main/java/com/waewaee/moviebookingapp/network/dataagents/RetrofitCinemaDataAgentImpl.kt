package com.waewaee.moviebookingapp.network.dataagents

import com.waewaee.moviebookingapp.data.vos.CheckoutMovieInfoVO
import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import com.waewaee.moviebookingapp.network.CinemaApi
import com.waewaee.moviebookingapp.network.request.VoucherRequest
import com.waewaee.moviebookingapp.network.responses.*
import com.waewaee.moviebookingapp.utils.CINEMA_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitCinemaDataAgentImpl: CinemaDataAgent {

    private var mCinemaApi: CinemaApi? = null

    init {
        val mOkHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(CINEMA_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mCinemaApi = retrofit.create(CinemaApi::class.java)
    }

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit,
        setToken: (String) -> Unit,
    ) {
        mCinemaApi?.getLoginWithEmail(email = email, password = password)
            ?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
//                        var errorVO = ErrorVO(code = response.body()?.code ?: 404, message = response.body()?.message ?: "Not Found")
                        onSuccess(response.body() ?: LoginResponse())
                        setToken(response.body()?.token ?: "")
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun getSignUpWithEmail(
        name: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        mCinemaApi?.getSignUpWithEmail(name = name, email = email, password = password, phone = phone)
            ?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: LoginResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun getProfile(
        authorization: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getProfile(authorization = authorization)
            ?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: LoginResponse())
                    } else  {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun logout(
        authorization: String,
        onSuccess: (LoginResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.logout(authorization = authorization)
            ?.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: LoginResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun getCinemaTimeslots(
        date: String,
        authorization: String,
        onSuccess: (TimeslotsResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getCinemaTimeslots(authorization = authorization, date = date)
            ?.enqueue(object : Callback<TimeslotsResponse> {
                override fun onResponse(
                    call: Call<TimeslotsResponse>,
                    response: Response<TimeslotsResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: TimeslotsResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<TimeslotsResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun getSeatingPlan(
        timeslotId: Int,
        date: String,
        authorization: String,
        onSuccess: (SeatingPlanResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSeatingPlan(timeslotId = timeslotId, date = date, authorization = authorization)
            ?.enqueue(object : Callback<SeatingPlanResponse> {
                override fun onResponse(
                    call: Call<SeatingPlanResponse>,
                    response: Response<SeatingPlanResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: SeatingPlanResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SeatingPlanResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            } )
    }

    override fun getSnackList(
        authorization: String,
        onSuccess: (SnackListResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getSnackList(authorization = authorization)
            ?.enqueue(object: Callback<SnackListResponse> {
                override fun onResponse(
                    call: Call<SnackListResponse>,
                    response: Response<SnackListResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: SnackListResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SnackListResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun getPaymentMethods(
        authorization: String,
        onSuccess: (PaymentMethodResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.getPaymentMethods(authorization = authorization)
            ?.enqueue(object: Callback<PaymentMethodResponse> {
                override fun onResponse(
                    call: Call<PaymentMethodResponse>,
                    response: Response<PaymentMethodResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: PaymentMethodResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }


            })
    }

    override fun addNewCard(
        authorization: String,
        cardNumber: String,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (AddNewCardResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.addNewCard(authorization = authorization, cardNumber = cardNumber, cardHolder = cardHolder, expirationDate = expirationDate, cvc = cvc)
            ?.enqueue(object : Callback<AddNewCardResponse> {
                override fun onResponse(
                    call: Call<AddNewCardResponse>,
                    response: Response<AddNewCardResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body() ?: AddNewCardResponse())
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<AddNewCardResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

    override fun checkout(
        authorization: String,
        voucherRequest: VoucherRequest,
        onSuccess: (CheckoutResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mCinemaApi?.checkOut(authorization = authorization, voucherRequest = voucherRequest)
            ?.enqueue(object : Callback<CheckoutResponse> {
                override fun onResponse(
                    call: Call<CheckoutResponse>,
                    response: Response<CheckoutResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.code != 200) {
                            onFailure(response.body()?.message ?: "Server Error")
                        } else {
                            onSuccess(response.body() ?: CheckoutResponse())
                        }
                    }else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                    onFailure(t.message ?: "Failed")
                }

            })
    }

}