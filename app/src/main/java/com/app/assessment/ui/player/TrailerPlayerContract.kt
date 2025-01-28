package com.app.assessment.ui.player




interface TrailerPlayerContract {
    interface View {
        fun showLoading()
        fun hideLoading()

        fun startTrailerPlayerActivity(trailerKey: String)
    }

    interface Presenter {
        fun getTrailerKey(movieId: Int, apiKey: String)
    }
}
