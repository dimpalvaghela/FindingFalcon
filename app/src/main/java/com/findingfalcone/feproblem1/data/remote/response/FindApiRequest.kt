package com.findingfalcone.feproblem1.data.remote.response

import com.google.gson.annotations.SerializedName


data class FindApiRequest(
    val token: String,
    @SerializedName("planet_names")
     val planetNames: List<String>,
    @SerializedName("vehicle_names")
    val VehicleNames: List<String>
)