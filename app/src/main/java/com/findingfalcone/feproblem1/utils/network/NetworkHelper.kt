package com.findingfalcone.feproblem1.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.findingfalcone.feproblem1.utils.log.LogUtils
import com.findingfalcone.feproblem1.utils.log.Logger
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import org.json.JSONObject
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Singleton


@Singleton
class NetworkHelper constructor(private val context: Context) {

    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

    fun castToNetworkError(throwable: Throwable): NetworkError {
        val defaultNetworkError = NetworkError()
        try {
            if (throwable is ConnectException) {
                LogUtils.displayELog(
                    "NetworkHelper",
                    "castToNetworkError ConnectException " + throwable
                )
                return NetworkError(0, "0")
            }
            if (throwable !is HttpException) {
                LogUtils.displayELog(
                    "NetworkHelper",
                    "castToNetworkError HttpException " + throwable
                )
                LogUtils.displayELog(
                    "NetworkHelper",
                    "castToNetworkError HttpException1 " + throwable.message
                )

                if (throwable is UnknownHostException) {
                    LogUtils.displayELog(
                        "NetworkHelper",
                        "castToNetworkError UnknownHostException " + throwable.message
                    )

                    return NetworkError(
                        404,
                        NetworkStatus.CODE_401,
                        NetworkStatus.UNKNOWN_HOST_EXCEPTION,
                        null
                    )
                }

                try {
                    val jObjError = JSONObject(throwable.message)
                    Log.d("Tag", " Network helper Get error response jObjError " + jObjError)
                    var error = Gson().fromJson(jObjError.toString(), NetworkError::class.java)
                    error.message = error.meta!!.msg!!.message!!
                    return NetworkError(
                        NetworkStatus.CODE_THROW,
                        NetworkStatus.CODE_THROW_STATUS,
                        error.message,
                        error.meta
                    )
                } catch (e: Exception) {

                }

                LogUtils.displayELog(
                    "NetworkHelper",
                    "castToNetworkError defaultNetworkError " + defaultNetworkError
                )
                return defaultNetworkError
            }

            try {
                Log.d(
                    "Tag",
                    " Network helper Get error response code " + throwable.response().errorBody()
                )
                var errorBody = throwable.response()?.errorBody()?.string()
                Log.d("Tag", " Network helper Get error response errorBody " + errorBody)

                var error: NetworkError
                if (!errorBody.isNullOrBlank()) {
                    Log.d("Tag", " Network helper Get error response is not null ")
                    val jObjError = JSONObject(errorBody)
                    Log.d("Tag", " Network helper Get error response jObjError " + jObjError)
                    error = Gson().fromJson(jObjError.toString(), NetworkError::class.java)
                    error.message = error.meta!!.msg!!.message!!
                    Log.d(
                        "Tag",
                        " Network helper Get error response jObjError " + error.message
                    )

                    return NetworkError(
                        throwable.code(),
                        error.statusCode,
                        error.message,
                        error.meta
                    )
                } else {

                    var errorcode = throwable.code()
                    Log.d("Tag", " Network helper errorcode : $errorcode")
                    /*error = GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()
                        .fromJson(throwable.response().errorBody()?.string(), NetworkError::class.java)*/
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: IOException) {
            Logger.e(TAG, e.toString())
        } catch (e: JsonSyntaxException) {
            Logger.e(TAG, e.toString())
        } catch (e: NullPointerException) {
            Logger.e(TAG, e.toString())
        }
        return defaultNetworkError
    }
}