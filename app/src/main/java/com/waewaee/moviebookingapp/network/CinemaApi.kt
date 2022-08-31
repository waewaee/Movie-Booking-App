package com.waewaee.moviebookingapp.network

import com.waewaee.moviebookingapp.data.vos.VisaCardVO
import com.waewaee.moviebookingapp.network.request.VoucherRequest
import com.waewaee.moviebookingapp.network.responses.*
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

    @GET(API_GET_SEATING_PLAN)
    fun getSeatingPlan(
        @Header(PARAM_AUTHORIZATION) authorization: String,
        @Query(PARAM_TIMESLOT_ID) timeslotId: Int,
        @Query(PARAM_BOOKING_DATE) date:String,
    ) : Call<SeatingPlanResponse>

    @GET(API_GET_SNACKLIST)
    fun getSnackList(
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<SnackListResponse>

    @GET(API_GET_PAYMENT_METHODS)
    fun getPaymentMethods(
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<PaymentMethodResponse>

    @POST(API_CREATE_CARD)
    @FormUrlEncoded
    fun addNewCard(
        @Header(PARAM_AUTHORIZATION) authorization: String,
        @Field(PARAM_CARD_NUMBER) cardNumber: String,
        @Field(PARAM_CARD_HOLDER) cardHolder: String,
        @Field(PARAM_EXPIRATION_DATE) expirationDate: String,
        @Field(PARAM_CVC) cvc: Int,
    ) : Call<AddNewCardResponse>

    @POST(API_CHECKOUT)
    fun checkOut(
        @Header(PARAM_AUTHORIZATION) authorization: String,
        @Body voucherRequest: String,
    ) : Call<CheckoutResponse>

}