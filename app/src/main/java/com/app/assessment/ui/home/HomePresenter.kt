package com.app.assessment.ui.home

import com.app.assessment.data.repository.MovieRepository
import com.app.assessment.utils.Constants.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePresenter(
    private val view: HomeContract.View,
    private val repository: MovieRepository
) : HomeContract.Presenter {

    override fun loadMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = repository.getNowPlayingMovies(API_KEY)
                view.showMovies(response.results)
            } catch (e: Exception) {
                view.showError(e.message ?: "An error occurred")
            }
        }
    }

    override fun loadTrending() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = repository.getTrendingMovies(API_KEY)
                response.results?.let { view.showTrendingMovies(it) }
            } catch (e: Exception) {
                view.showError(e.message ?: "An error occurred")
            }
        }
    }

    override fun loadPopular() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val responsePopularDTO = repository.getPopularMovies(API_KEY)
                view.showPopularMovies(responsePopularDTO.results)
            } catch (e: Exception) {
                view.showError(e.message ?: "An error occurred")
            }
        }
    }

    override fun loadTopRated() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val responseTopRatedDTO = repository.getTopRatedMovies(API_KEY)
                view.showTopRatedMovies(responseTopRatedDTO.results)
            } catch (e: Exception) {
                view.showError(e.message ?: "An error occurred")
            }
        }
    }

    override fun loadUpcoming() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val responseUpcomingDTO = repository.getUpcomingMovies(API_KEY)
                view.showUpcomingMovies(responseUpcomingDTO.results)
            } catch (e: Exception) {
                view.showError(e.message ?: "An error occurred")
            }
        }
    }
}