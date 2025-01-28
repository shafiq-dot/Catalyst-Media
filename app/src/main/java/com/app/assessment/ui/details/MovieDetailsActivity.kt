package com.app.assessment.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.assessment.R
import com.app.assessment.data.api.ApiService
import com.app.assessment.data.api.RetrofitInstance
import com.app.assessment.data.model.Cast
import com.app.assessment.data.model.Genres
import com.app.assessment.data.repository.MovieRepository
import com.app.assessment.ui.player.TrailerPlayerActivity
import com.app.assessment.ui.player.TrailerPlayerContract
import com.app.assessment.ui.player.TrailerPlayerPresenter
import com.app.assessment.utils.Constants.API_KEY
import com.app.assessment.utils.Constants.IMAGE_BASE_URL
import com.app.assessment.utils.DateUtils.formatReleaseDate
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso


class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.View, TrailerPlayerContract.View  {

    private lateinit var presenter: MovieDetailsPresenter
    private lateinit var Trailerpresenter: TrailerPlayerPresenter

    private var recyclerViewCast: RecyclerView? = null
    private var moviePoster: ImageView? = null
    private var MovieBackPoster: ImageView? = null
    private var movieTitle: TextView? = null
    private var movieOverview: TextView? = null
    private var releaseDateText: TextView? = null
    private var releaseDateText2: TextView? = null
    private var movieDurationText: TextView? = null
    private var movieDurationText2: TextView? = null
    private var movieType: TextView? = null
    private var movieType2: TextView? = null
    private var movieId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        recyclerViewCast=findViewById(R.id.castRecyclerview)
        movieTitle=findViewById(R.id.movieTitle)
        movieOverview=findViewById(R.id.movieOverview)
        moviePoster=findViewById(R.id.moviePoster)
        MovieBackPoster=findViewById(R.id.backPoster)
        releaseDateText=findViewById(R.id.movieYear)
        releaseDateText2=findViewById(R.id.movieYear2)
        movieDurationText=findViewById(R.id.movieDuration)
        movieDurationText2=findViewById(R.id.movieDuration2)
        movieType=findViewById(R.id.movieType)
        movieType2=findViewById(R.id.movieType2)

        movieId = intent.getIntExtra("movie_id", -1)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        adjustFontScale(this, 1.0f)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout2)

        // Add tabs manually

        val tabs = listOf("About Movie", "Cast")

        tabs.forEachIndexed { index, tabTitle ->
            val tab = tabLayout.newTab()
            val customTabView = layoutInflater.inflate(R.layout.tab_custom_view, null) as TextView
            customTabView.text = tabTitle
            tab.customView = customTabView
            tabLayout.addTab(tab)
        }

        // Handle Tab Clicks with Listener
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedTab = tab?.customView as? TextView
                selectedTab?.setTextColor(getColor(R.color.selected_tab_white_color))

                if (selectedTab?.text != null) {
                    when (selectedTab.text.toString().trim()) {
                        "About Movie" -> {
                            recyclerViewCast?.visibility=View.GONE
                            movieOverview?.visibility=View.VISIBLE

                        }
                        "Cast" -> {
                            recyclerViewCast?.visibility=View.VISIBLE

                            movieOverview?.visibility=View.GONE
                        }

                    }
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val unselectedTab = tab?.customView as? TextView
                unselectedTab?.setTextColor(getColor(R.color.white))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val reselectedTab = tab?.customView as? TextView
                Toast.makeText(this@MovieDetailsActivity, "${reselectedTab?.text} reselected", Toast.LENGTH_SHORT).show()
            }
        })


        moviePoster?.setOnClickListener {

            fetchTrailer()
        }

        supportActionBar?.apply {
            title = getString(R.string.Detail)
            val actionBarTitle = findViewById<TextView>(android.R.id.title)
            actionBarTitle?.gravity = Gravity.CENTER

            setDisplayHomeAsUpEnabled(true)  // This enables the back icon
            setDisplayShowHomeEnabled(true)
        }


        val actionBar = supportActionBar
        val actionBarTitle = actionBar?.customView?.findViewById<TextView>(android.R.id.title)
        actionBarTitle?.textSize = 14f // Set text size in SP


        val movieId = intent.getIntExtra("movie_id", 0)
        val apiService = RetrofitInstance.createService(ApiService::class.java)
        val repository = MovieRepository(apiService)
        presenter = MovieDetailsPresenter(this, repository)
        Trailerpresenter = TrailerPlayerPresenter(this, apiService)

        recyclerViewCast?.layoutManager = GridLayoutManager(this, 2)
        presenter.loadMovieDetails(movieId)
    }

    override fun showMovieDetails(
        title: String,
        overview: String,
        posterPath: String,
        releaseDate: String,
        backPoster: String,
        year: String,
        time: String,
        type: String,
        genres: List<Genres>,
    ) {
        movieTitle?.text = title
        movieOverview?.text = overview
        releaseDateText?.text = formatReleaseDate(releaseDate)
        releaseDateText2?.text = formatReleaseDate(releaseDate)
        movieDurationText?.text = time + " Minutes"
        movieDurationText2?.text = time + " Minutes"
        if (genres.size > 1) {
            movieType?.text = genres[1].name  // Assuming Genres has a 'name' property
            movieType2?.text = genres[1].name  // Assuming Genres has a 'name' property
        } else {
            movieType?.text = "No Genre Available"
            movieType2?.text = "No Genre Available"
        }

        Picasso.get().load("$IMAGE_BASE_URL$posterPath").into(moviePoster)
        Picasso.get().load("$IMAGE_BASE_URL$backPoster").into(MovieBackPoster)
    }




    private fun fetchTrailer() {
        movieId?.let {
            Trailerpresenter.getTrailerKey(it, API_KEY)
        }
    }



    override fun showLoading() {
        // Show loading (e.g., ProgressBar)
    }

    override fun hideLoading() {
        // Hide loading (e.g., ProgressBar)
    }



    override fun startTrailerPlayerActivity(trailerKey: String) {
        TODO("Not yet implemented")
    }


    override fun showCastDetails(cast: List<Cast>) {
        val adapter = CastAdapter(cast)
        recyclerViewCast?.adapter = adapter
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the back button press
                onBackPressed() // Finishes the current activity and returns to the previous one
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun adjustFontScale(context: Context, scale: Float): Context {

        val configuration = context.resources.configuration
        configuration.fontScale = scale

        // Create a new configuration context
        val newContext = context.createConfigurationContext(configuration)

        // Update display metrics based on the new configuration
        val metrics = newContext.resources.displayMetrics
        metrics.density = metrics.density * scale // Adjusting density

        return newContext
    }
}
