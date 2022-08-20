package com.waewaee.moviebookingapp.network

import com.waewaee.moviebookingapp.network.responses.MovieListResponse
import com.waewaee.moviebookingapp.utils.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

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
}