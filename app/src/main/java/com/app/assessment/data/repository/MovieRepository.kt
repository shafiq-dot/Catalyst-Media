package com.app.assessment.data.repository

import com.app.assessment.data.api.ApiService

class MovieRepository(private val apiService: ApiService) {
    suspend fun getNowPlayingMovies(apiKey: String) = apiService.getNowPlayingMovies(apiKey)
    suspend fun getPopularMovies( apiKey: String) = apiService.getPopularMovie( apiKey)
    suspend fun getTopRatedMovies( apiKey: String) = apiService.getTopRatedMovie( apiKey)
    suspend fun getUpcomingMovies( apiKey: String) = apiService.getUpcomingMovie( apiKey)
    suspend fun getTrendingMovies( apiKey: String) = apiService.getTrending( apiKey)

    suspend fun getCastDetails(movieId: Int, apiKey: String) = apiService.getCastDetail(movieId, apiKey)
    suspend fun getMovieDetails(movieId: Int, apiKey: String) = apiService.getMovieDetail(movieId, apiKey)
    suspend fun getTrailerVideo(movieId: Int, apiKey: String) = apiService.getTrailer(movieId, apiKey)
}