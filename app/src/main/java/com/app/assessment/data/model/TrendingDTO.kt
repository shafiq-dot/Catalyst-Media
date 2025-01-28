package com.app.assessment.data.model

import com.google.gson.annotations.SerializedName

class TrendingDTO {

    @SerializedName("page"          ) var page         : Int?               = null
    @SerializedName("results"       ) var results      : ArrayList<Movie>? = null
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null
    @SerializedName("total_results" ) var totalResults : Int?               = null

}