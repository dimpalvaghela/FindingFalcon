package com.findingfalcone.feproblem1.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class
Token(
    @Expose
    @SerializedName("token")
    val token: String,
)