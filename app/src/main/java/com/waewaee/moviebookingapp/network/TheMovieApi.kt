package com.waewaee.moviebookingapp.network

import com.waewaee.moviebookingapp.network.responses.CreditsByMovieResponse
import com.waewaee.moviebookingapp.network.responses.MovieListResponse
import com.waewaee.moviebookingapp.utils.*
import com.waewaee.themovieapp.data.vos.MovieVO
import retrofit2.Call
import retrofit2.http.*

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING_MOVIES)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : Call<MovieListResponse>

    @GET(API_GET_COMING_SOON_MOVIES)
    fun getComingSoonMovies(
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page : Int = 1,
    ) : Call<MovieListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ) : Call<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : Call<CreditsByMovieResponse>
}