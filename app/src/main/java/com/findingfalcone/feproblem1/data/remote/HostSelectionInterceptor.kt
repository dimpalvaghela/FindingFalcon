package com.findingfalcone.feproblem1.data.remote

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.URISyntaxException
import javax.inject.Singleton


/*
 Solution from @swankjesse
 Host Selection Retrofit
 More at @link https://github.com/square/retrofit/issues/1404#issuecomment-207408548
*/
@Singleton
class HostSelectionInterceptor :
    Interceptor {

    @Volatile
    private var host = HttpUrl.parse(APIServer.getCurrentBaseUrl())

    fun setHostBaseUrl() {
        host = HttpUrl.parse(APIServer.getCurrentBaseUrl())
        //APIServer.getCurrentBaseUrl()
        /*if (preferenceHelper.isProdEnvironment()) {
            host = HttpUrl.parse(BuildConfig.PRODUCTION_BASE_URL)
        } else {
            host = HttpUrl.parse(BuildConfig.DEVELOPMENT_BASE_URL)
        }*/
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (host != null) {
            var newUrl: HttpUrl? = null
            try {
                newUrl = request.url().newBuilder()
                    .scheme(host!!.scheme())
                    .host(host!!.url().toURI().host)
                    .build()
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            assert(newUrl != null)
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }

    init {
        setHostBaseUrl()
    }
}