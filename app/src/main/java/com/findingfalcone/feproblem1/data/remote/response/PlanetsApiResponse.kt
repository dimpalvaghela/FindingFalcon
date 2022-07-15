package com.findingfalcone.feproblem1.data.remote.response


import com.google.gson.annotations.SerializedName

class PlanetsApiResponse : ArrayList<PlanetsApiResponse.PlanetResponseItem>(){
    data class PlanetResponseItem(
        @SerializedName("distance")
        val distance: Int,
        @SerializedName("name")
        val name: String
    )
}