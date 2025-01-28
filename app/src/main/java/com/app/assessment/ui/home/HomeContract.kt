package com.app.assessment.ui.home

import com.app.assessment.data.model.Movie

interface HomeContract {
    interface View {
        fun showMovies(movies: List<Movie>)
        fun showTrendingMovies(movies: List<Movie>)
        fun showPopularMovies(movies: List<Movie>)
        fun showTopRatedMovies(movies: List<Movie>)
        fun showUpcomingMovies(movies: List<Movie>)
        fun showError(message: String)
    }

    interface Presenter {
        fun loadMovies()
        fun loadTrending()
        fun loadPopular()
        fun loadTopRated()
        fun loadUpcoming()
    }
}