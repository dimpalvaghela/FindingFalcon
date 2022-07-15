package com.findingfalcone.feproblem1.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.findingfalcone.feproblem1.R
import com.findingfalcone.feproblem1.utils.common.Resource
import com.findingfalcone.feproblem1.utils.log.LogUtils
import com.findingfalcone.feproblem1.utils.network.NetworkHelper
import com.findingfalcone.feproblem1.utils.network.NetworkStatus
import com.findingfalcone.feproblem1.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()
    val messageError: MutableLiveData<String> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
          //  messageStringId.postValue(Resource.error(R.string.app_common_network_no_internet_connection))
            false
        }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()


    protected open fun forcedLogoutUser() {
        // do something
    }

    abstract fun onCreate()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                Log.d("BaseViewModel","Handle network error status $status Message: "+message)
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.app_common_network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.app_common_server_connection_error))
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        forcedLogoutUser()
                        messageError.postValue(message)
                        //messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.app_common_network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.app_common_network_server_not_available))
                    else -> {
                        if(statusCode == NetworkStatus.CODE_401){
                            LogUtils.displayELog("NetworkHelper","castToNetworkError statusCode "+message)
                            //Alert to ask user to change app language
                            messageError.postValue(message)
                        }else {
                            messageString.postValue(Resource.error(message))
                        }
                    }
                }
            }
        }

}