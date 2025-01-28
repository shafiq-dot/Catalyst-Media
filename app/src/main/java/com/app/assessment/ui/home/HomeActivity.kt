package com.app.assessment.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.assessment.R
import com.app.assessment.data.api.ApiService
import com.app.assessment.data.api.RetrofitInstance
import com.app.assessment.data.model.Movie
import com.app.assessment.data.repository.MovieRepository
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var presenter: HomePresenter


    var recyclerViewTrending: RecyclerView? = null
    var recyclerViewMovie: RecyclerView? = null
    var recyclerViewPopular: RecyclerView? = null
    var recyclerViewRated: RecyclerView? = null
    var recyclerViewUpcoming: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerViewTrending=findViewById(R.id.recyclerView)
        recyclerViewMovie=findViewById(R.id.recyclerView2)
        recyclerViewPopular=findViewById(R.id.recyclerView3)
        recyclerViewRated=findViewById(R.id.recyclerView4)
        recyclerViewUpcoming=findViewById(R.id.recyclerView5)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        adjustFontScale(this, 1.0f)

        supportActionBar?.title = getString(R.string.what_do_you_want)

        val actionBar = supportActionBar
        val actionBarTitle = actionBar?.customView?.findViewById<TextView>(android.R.id.title)
        actionBarTitle?.textSize = 14f // Set text size in SP

        val apiService = RetrofitInstance.createService(ApiService::class.java)
        val repository = MovieRepository(apiService)
        presenter = HomePresenter(this, repository)

        recyclerViewTrending?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewMovie?.layoutManager = GridLayoutManager(this, 3)
        recyclerViewPopular?.layoutManager = GridLayoutManager(this, 3)
        recyclerViewRated?.layoutManager = GridLayoutManager(this, 3)
        recyclerViewUpcoming?.layoutManager = GridLayoutManager(this, 3)

        presenter.loadMovies()
        presenter.loadTrending()
        presenter.loadPopular()
        presenter.loadTopRated()
        presenter.loadUpcoming()


        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        // Add tabs manually

        val tabs = listOf("Now Playing", "Popular", "Top Rated", "Upcoming")

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
                        "Now Playing" -> {
                            recyclerViewMovie?.visibility = View.VISIBLE
                            recyclerViewPopular?.visibility = View.GONE
                            recyclerViewRated?.visibility = View.GONE
                            recyclerViewUpcoming?.visibility = View.GONE

                        }
                        "Popular" -> {
                            recyclerViewMovie?.visibility = View.GONE
                            recyclerViewPopular?.visibility = View.VISIBLE
                            recyclerViewRated?.visibility = View.GONE
                            recyclerViewUpcoming?.visibility = View.GONE
                        }
                        "Top Rated" -> {
                            recyclerViewMovie?.visibility = View.GONE
                            recyclerViewPopular?.visibility = View.GONE
                            recyclerViewRated?.visibility = View.VISIBLE
                            recyclerViewUpcoming?.visibility = View.GONE
                        }
                        "Upcoming" -> {
                            recyclerViewMovie?.visibility = View.GONE
                            recyclerViewPopular?.visibility = View.GONE
                            recyclerViewRated?.visibility = View.GONE
                            recyclerViewUpcoming?.visibility = View.VISIBLE
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
                Toast.makeText(this@HomeActivity, "${reselectedTab?.text} reselected", Toast.LENGTH_SHORT).show()
            }
        })



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

    override fun showMovies(movies: List<Movie>) {
        val adapter = MovieAdapter(movies)
        recyclerViewMovie?.adapter = adapter
    }

    override fun showTrendingMovies(movies: List<Movie>) {
        val adapter = TrendingAdapter(movies)
        recyclerViewTrending?.adapter = adapter
    }

    override fun showPopularMovies(movies: List<Movie>) {
        val adapter = MovieAdapter(movies)
        recyclerViewPopular?.adapter = adapter
    }

    override fun showTopRatedMovies(movies: List<Movie>) {
        val adapter = MovieAdapter(movies)
        recyclerViewRated?.adapter = adapter
    }

    override fun showUpcomingMovies(movies: List<Movie>) {
        val adapter = MovieAdapter(movies)
        recyclerViewUpcoming?.adapter= adapter
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }







}