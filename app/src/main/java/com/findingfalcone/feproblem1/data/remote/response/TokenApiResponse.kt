package com.findingfalcone.feproblem1.data.remote.response


import com.google.gson.annotations.SerializedName

data class TokenApiResponse(
    @SerializedName("token")
    val token: String
)