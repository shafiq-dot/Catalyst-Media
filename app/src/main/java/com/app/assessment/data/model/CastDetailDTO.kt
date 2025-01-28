package com.app.assessment.data.model

import com.google.gson.annotations.SerializedName

class CastDetailDTO {

    @SerializedName("id"   ) var id   : Int?            = null
    @SerializedName("cast" ) var cast : ArrayList<Cast> = arrayListOf()
    @SerializedName("crew" ) var crew : ArrayList<Crew> = arrayListOf()
}