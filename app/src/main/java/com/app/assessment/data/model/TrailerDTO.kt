package com.app.assessment.data.model

import com.google.gson.annotations.SerializedName

class TrailerDTO {
    @SerializedName("id"      ) var id      : Int?               = null
    @SerializedName("results" ) var results : ArrayList<Video> = arrayListOf()
}