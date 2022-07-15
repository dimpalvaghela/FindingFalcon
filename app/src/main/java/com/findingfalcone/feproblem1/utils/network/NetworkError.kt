package com.findingfalcone.feproblem1.utils.network

import com.findingfalcone.feproblem1.R
import com.findingfalcone.feproblem1.FalconeApplication
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkError(
    val status: Int = -1,
    @Expose
    @SerializedName("statusCode")
    val statusCode: String = "-1",
    @Expose
    @SerializedName("message")
    var message: String = FalconeApplication.appContext.getString(R.string.app_common_network_default_error),

    @SerializedName("meta") var meta: Meta? = Meta()
) {
    class Meta(

        @SerializedName("locale") var locale: String? = null,
        @SerializedName("msg") var msg: Msg? = Msg()

    ) {
        data class Msg(

            @SerializedName("success") var success: Boolean? = null,
            @SerializedName("error") var error: String? = null,
            @SerializedName("message") var message: String? = null,
            @SerializedName("error_details") var errorDetails: String? = null

        )
    }
}


