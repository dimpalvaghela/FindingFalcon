package com.findingfalcone.feproblem1.data.remote

import android.util.Log
import com.findingfalcone.feproblem1.BuildConfig
import com.findingfalcone.feproblem1.data.local.prefs.ConstantsPref
import com.findingfalcone.feproblem1.data.local.prefs.PaperDbHelper
import com.findingfalcone.feproblem1.utils.common.Constants

object APIServer {

    const val API_VERSION = "api/v"

    var CurrentBaseURL = ""

    fun getMainBaseUrl(): String {
        return BuildConfig.API_URL
    }

    fun getCurrentBaseUrl(): String {
        return PaperDbHelper.read(ConstantsPref.CURRENT_BASE_URL, BuildConfig.API_URL)
    }

    fun changeCurrentUrl(url: String) {
        Log.e("Tag", "URL changeCurrentUrl baseUrl$url")
        PaperDbHelper.write(ConstantsPref.CURRENT_BASE_URL, url)
    }

    fun getSamedisUrl(): String {
        var baseUrl = "${getCurrentBaseUrl()}${Constants.STRING_SLASH}${APIServer.API_VERSION}"
        Log.d("Tag", "URL getSamedisUrl baseUrl$baseUrl")
        return baseUrl
    }

    fun getUrl(version: String): String {
        var finalBaseUrl = "${getSamedisUrl()}${version}${Constants.STRING_SLASH}"
        Log.d("Tag", "URL getUrl finalBaseUrl ${finalBaseUrl}")
        return finalBaseUrl
    }
}