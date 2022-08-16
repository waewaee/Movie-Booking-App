package com.waewaee.moviebookingapp.network.dataagents

import com.waewaee.moviebookingapp.data.vos.ErrorVO
import com.waewaee.moviebookingapp.network.CinemaApi
import com.waewaee.moviebookingapp.network.responses.LoginResponse
import com.waewaee.moviebookingapp.utils.BASE_URL
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
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mCinemaApi = retrofit.create(CinemaApi::class.java)
    }

    override fun getLoginWithEmail(
        email: String,
        password: String,
        onSuccess: (ErrorVO) -> Unit,
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
                        var errorVO = ErrorVO(code = response.body()?.code ?: 404, message = response.body()?.message ?: "Not Found")
                        onSuccess(errorVO)
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
        onFailure: (String) -> Unit
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

}