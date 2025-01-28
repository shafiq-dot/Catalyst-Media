package com.app.assessment.data.api

import com.app.assessment.data.model.CastDetailDTO
import com.app.assessment.data.model.MovieDetailDTO
import com.app.assessment.data.model.NowPlayingDTO
import com.app.assessment.data.model.PopularDTO
import com.app.assessment.data.model.TopRatedDTO
import com.app.assessment.data.model.TrailerDTO
import com.app.assessment.data.model.TrendingDTO
import com.app.assessment.data.model.UpcomingDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): NowPlayingDTO


    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PopularDTO

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TopRatedDTO

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): UpcomingDTO

    @GET("trending/movie/day")
    suspend fun getTrending(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TrendingDTO

    @GET("movie/{movie_id}/credits")
    suspend fun getCastDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): CastDetailDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailDTO

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): TrailerDTO







}