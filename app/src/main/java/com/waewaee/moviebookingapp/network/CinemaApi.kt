package com.waewaee.moviebookingapp.network

import com.waewaee.moviebookingapp.network.responses.LoginResponse
import com.waewaee.moviebookingapp.network.responses.MovieListResponse
import com.waewaee.moviebookingapp.network.responses.TimeslotsResponse
import com.waewaee.moviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface CinemaApi {

    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun getLoginWithEmail(
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String,
    ) : Call<LoginResponse>

    @POST(API_SIGN_UP_WITH_EMAIL)
    @FormUrlEncoded
    fun getSignUpWithEmail(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String,
        @Field(PARAM_PHONE) phone: String,
    ) : Call<LoginResponse>

    @GET(API_GET_PROFILE)
    fun getProfile(
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<LoginResponse>

    @POST(API_LOGOUT)
    fun logout(
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<LoginResponse>

    @Headers("Accept: application/json")
    @GET(API_GET_CINEMA_TIMESLOTS)
    fun getCinemaTimeslots(
        @Query(PARAM_DATE) date: String,
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<TimeslotsResponse>

}