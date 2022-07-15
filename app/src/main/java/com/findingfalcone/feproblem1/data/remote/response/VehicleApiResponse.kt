package com.findingfalcone.feproblem1.data.remote.response


import com.findingfalcone.feproblem1.R
import com.google.gson.annotations.SerializedName

class VehicleApiResponse : ArrayList<VehicleApiResponse.VehicleResponseItem>() {
    data class VehicleResponseItem(
        @SerializedName("max_distance")
        val maxDistance: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("speed")
        val speed: Int,
        @SerializedName("total_no")
        val totalNo: Int,
        var isSelected: Boolean

    ) /*{
        fun getImage() = when (name) {
            "Space pod" -> R.drawable.ic_space_icon
            "Space rocket" -> R.drawable.ic_space_icon
            "Space shuttle" -> R.drawable.ic_space_icon
            "Space ship" -> R.drawable.ic_space_icon
            else -> throw NoSuchElementException("unknown vehicle")
        }
    }*/
}