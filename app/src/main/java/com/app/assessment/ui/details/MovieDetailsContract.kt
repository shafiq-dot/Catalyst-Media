package com.app.assessment.ui.details

import com.app.assessment.data.model.Cast
import com.app.assessment.data.model.Genres


interface MovieDetailsContract {
    interface View {
        fun showMovieDetails(title: String, overview: String, posterPath: String, releaseDate: String, backPoster: String, year :String, time : String, type:String, genres: List<Genres>)
        fun showCastDetails(cast: List<Cast>)

    }

    interface Presenter {
        fun loadMovieDetails(movieId: Int)
    }
}
