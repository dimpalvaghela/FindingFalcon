package com.findingfalcone.feproblem1.utils.log

import android.util.Log
import com.findingfalcone.feproblem1.BuildConfig

object LogUtils {
    fun displayLog(tag: String?, message: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message!!)
        }
    }

    fun displayELog(tag: String?, message: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message!!)
        }
    }

    fun displayILog(tag: String?, message: String?) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message!!)
        }
    }
}