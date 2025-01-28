package com.app.assessment.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.assessment.R
import com.app.assessment.data.model.Movie
import com.app.assessment.ui.details.MovieDetailsActivity
import com.app.assessment.utils.Constants.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class TrendingAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<TrendingAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieTrendingPoster: ImageView = itemView.findViewById(R.id.movieTrendingPoster)

        fun bind(movie: Movie) {
            Picasso.get().load("$IMAGE_BASE_URL${movie.posterPath}")
                .into(movieTrendingPoster)

            itemView.setOnClickListener {
                // Create the Intent with the context and the target Activity class
                val intent = Intent(itemView.context, MovieDetailsActivity::class.java)

                // Use putExtra to add the data to the intent
                intent.putExtra("movie_id", movie.id) // Pass the movie ID
                intent.putExtra("movie_title", movie.title) // Optional: pass other details

                // Start the activity with the intent
                itemView.context.startActivity(intent)
            }

        }

    }

}