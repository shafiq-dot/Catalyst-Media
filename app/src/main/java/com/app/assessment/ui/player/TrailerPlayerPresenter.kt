package com.app.assessment.ui.player



import android.content.Context
import android.util.Log

import com.app.assessment.data.api.ApiService
import com.app.assessment.data.model.TrailerDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

class TrailerPlayerPresenter(
    private val view: TrailerPlayerContract.View,
    private val apiService: ApiService
) : TrailerPlayerContract.Presenter {

    override fun getTrailerKey(movieId: Int, apiKey: String) {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Log the movie ID and the API URL being requested
                val url = "https://api.themoviedb.org/3/movie/$movieId/videos?api_key=$apiKey"
                Log.d("TrailerPlayerPresenter", "Requesting trailer with URL: $url")

                val response: TrailerDTO = apiService.getTrailer(movieId, apiKey)

                // Log the response to check if results are empty
                Log.d("TrailerPlayerPresenter", "Trailer response: ${response.results}")

                // Check if the trailer key is available in the response
                val trailerKey = response.results.firstOrNull()?.key

                val youtubeKeys = response.results
                    .filter { it.site == "YouTube" } // Ensure the site is YouTube
                    .map { it.key }

                withContext(Dispatchers.Main) {
                    view.hideLoading()

                    // Handle case where no trailer is found
                    if (youtubeKeys.isNullOrEmpty()) {

                        Log.d("Error","No trailer available for this movie.")

                    } else {
                        // Start TrailerPlayerActivity with the trailer key

                        youtubeKeys.firstOrNull()?.let { Log.d("KeysLog", it) }
                        TrailerPlayerActivity.start(view as Context, youtubeKeys.firstOrNull().toString())
                    }
                }

            } catch (e: HttpException) {
                // Handle 404 error specifically
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (e.code() == 404) {

                        Log.d("Error","Trailer not found for this movie.")

                    } else {
                        Log.d("Error","Error fetching trailer: ${e.message()}")

                    }
                    Log.e("TrailerPlayerPresenter", "HTTP Error: ${e.message()}")
                }
            } catch (e: Exception) {
                // General error handling
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    Log.d("Error","An unexpected error occurred:  ${e.message}")

                    Log.e("TrailerPlayerPresenter", "Error: ${e.message}")
                }
            }
        }
    }



}
