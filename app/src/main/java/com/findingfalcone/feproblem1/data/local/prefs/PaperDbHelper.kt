package com.findingfalcone.feproblem1.data.local.prefs

import android.util.Log
import com.findingfalcone.feproblem1.data.remote.APIServer
import com.findingfalcone.feproblem1.utils.common.Constants
import com.findingfalcone.feproblem1.utils.log.Logger
import io.paperdb.Book
import io.paperdb.Paper

object PaperDbHelper {

    fun <T> read(key: String?, defaultValue: T): T {
        val value: T = Paper.book().read(key, defaultValue)
        return value ?: defaultValue
    }

    fun <T> write(key: String?, value: T?): Book? {
        if (value == null) {
            Logger.d("PaperDb", "Paper doesn't support writing null root values")
        }
        return Paper.book().write(key, value)
    }

    fun paperDbLogout() {
        var currentBaseUrl = APIServer.getCurrentBaseUrl()
        Log.e("Tag", "URL paperDbLogout currentBaseUrl $currentBaseUrl")
        Paper.book().destroy()

        //Store your current base URL
        APIServer.changeCurrentUrl(currentBaseUrl)
    }

    fun getLoginUserId(): String {
        return read(ConstantsPref.LOGIN_USER_ID, Constants.STRING_BLANK)
    }
}