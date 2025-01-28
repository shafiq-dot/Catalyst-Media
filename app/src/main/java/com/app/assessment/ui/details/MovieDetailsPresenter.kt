package com.app.assessment.ui.details


import com.app.assessment.data.repository.MovieRepository
import com.app.assessment.utils.Constants.API_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsPresenter(
    private val view: MovieDetailsContract.View,
    private val repository: MovieRepository
) : MovieDetailsContract.Presenter {

    override fun loadMovieDetails(movieId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val castResponse = repository.getCastDetails(movieId, API_KEY)
                view.showCastDetails(castResponse.cast)
            } catch (e: Exception) {

            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = repository.getMovieDetails(movieId,API_KEY)
                view.showMovieDetails(response.title.toString(),response.overview.toString(), response.posterPath.toString(), response.releaseDate.toString(), response.backdropPath.toString(), response.releaseDate.toString(), response.runtime.toString(), response.popularity.toString(), response.genres.toList())
            } catch (e: Exception) {

            }
        }
    }
}
