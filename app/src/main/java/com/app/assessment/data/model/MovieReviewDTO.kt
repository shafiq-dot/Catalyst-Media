package com.app.assessment.data.model

import com.google.gson.annotations.SerializedName

class MovieReviewDTO {
    @SerializedName("id"            ) var id           : Int?               = null
    @SerializedName("page"          ) var page         : Int?               = null
    @SerializedName("results"       ) var results      : ArrayList<Author> = arrayListOf()
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null
    @SerializedName("total_results" ) var totalResults : Int?               = null
}