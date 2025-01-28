package com.app.assessment.ui.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.app.assessment.R

class TrailerPlayerActivity : AppCompatActivity() {


    companion object {
        private const val TRAILER_KEY = "trailer_key"

        fun start(context: Context, trailerKey: String) {
            val intent = Intent(context, TrailerPlayerActivity::class.java)
            intent.putExtra(TRAILER_KEY, trailerKey)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer_player)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }


        val trailerKey = intent.getStringExtra(TRAILER_KEY)


        val webView: WebView = findViewById(R.id.youtubeWebView)

        // Enable JavaScript for the WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true

        // Set a WebViewClient to avoid opening in the browser
        webView.webViewClient = WebViewClient()

        // Load the YouTube video using the IFrame Player API
        val htmlData = """
            <html>
            <body style="margin:0;padding:0;">
                <iframe width="100%" height="100%" 
                        src="https://www.youtube.com/embed/$trailerKey" 
                        frameborder="0" allowfullscreen>
                </iframe>
            </body>
            </html>
        """
        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null)

    }

    override fun onPause() {
        super.onPause()
        findViewById<WebView>(R.id.youtubeWebView).onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<WebView>(R.id.youtubeWebView).destroy()
    }



}
