package com.findingfalcone.feproblem1.data.remote

import android.util.Log
import com.findingfalcone.feproblem1.BuildConfig
import com.findingfalcone.feproblem1.data.local.prefs.ConstantsPref
import com.findingfalcone.feproblem1.data.local.prefs.PaperDbHelper
import com.findingfalcone.feproblem1.utils.log.LogUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    const val HEADER_API_KEY = "x-api-key"
    const val HEADER_AUTHORIZATION = "Token"
    const val HEADER_ACCESS_TOKEN = "x-access-token"
    const val HEADER_USER_ID = "x-user-id"

    private const val NETWORK_CALL_TIMEOUT = 60
    internal var HEADER_AUTH_TOKEN: String = getAPIToken()

    fun getAPIToken(): String {
        return PaperDbHelper.read(ConstantsPref.API_TOKEN, "")
    }

    fun create(baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
        Log.d("Tag", "base url " + baseUrl)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .cache(Cache(cacheDir, cacheSize))
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                            else HttpLoggingInterceptor.Level.NONE
                        })
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val response = chain.proceed(request)
                        Log.d("API response: ", "Networking create api request: $request")
                        Log.d("API response: ", "Networking create api response: $response")
                        if (response != null) {
                            Log.d(
                                "API response: ",
                                "Networking create api response code: ${response.code()}"
                            )
                            Log.d(
                                "API response: ",
                                "Networking create api response body: ${response.body()}"
                            )
                        }
                        /*if (response.code() in 401..403) {
                            Log.e("LOGOUT INTERCEPTOR: ", "force logout")
                            val baseResponse = Gson().fromJson(response.body()!!.string(), BaseResponse::class.java)
                            ApplicationClass.activeActivity!!.runOnUiThread {
                                AlertDialog.Builder(ApplicationClass.activeActivity!!)
                                    .setTitle("")
                                    .setMessage(baseResponse.error)
                                    .setCancelable(false)
                                    .setPositiveButton("ok") { _: DialogInterface, i: Int ->
                                        //ApplicationClass.activeActivity!!.finishAffinity()
                                        PrefManager.instance.token = ""
                                        ApplicationClass.activeActivity!!.startActivity(ApplicationClass.activeActivity!!.landingPageIntent()
                                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                                    }.show()
                            }
                        }else if(response.code()==409){
                            AppUpdater.instance.open()
                        }*/
                        response
                    }
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }


}