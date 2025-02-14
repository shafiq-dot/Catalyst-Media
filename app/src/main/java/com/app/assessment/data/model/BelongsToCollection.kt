package com.app.assessment.data.model

import com.google.gson.annotations.SerializedName

class BelongsToCollection {
    @SerializedName("id"            ) var id           : Int?    = null
    @SerializedName("name"          ) var name         : String? = null
    @SerializedName("poster_path"   ) var posterPath   : String? = null
    @SerializedName("backdrop_path" ) var backdropPath : String? = null
}