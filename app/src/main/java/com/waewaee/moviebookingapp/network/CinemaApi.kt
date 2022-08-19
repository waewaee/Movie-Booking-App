package com.waewaee.moviebookingapp.network

import com.waewaee.moviebookingapp.network.responses.LoginResponse
import com.waewaee.moviebookingapp.network.responses.MovieListResponse
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
    @FormUrlEncoded
    fun getProfile(
        @Header(PARAM_AUTHORIZATION) authorization: String,
    ) : Call<LoginResponse>

    @GET(API_GET_NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : Call<MovieListResponse>

}