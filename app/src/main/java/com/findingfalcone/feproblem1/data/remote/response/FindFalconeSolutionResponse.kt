package com.findingfalcone.feproblem1.data.remote.response


import com.google.gson.annotations.SerializedName

data class FindFalconeSolutionResponse(
    @SerializedName("planet_name")
    val planetName: String,
    @SerializedName("status")
    val status: String
)