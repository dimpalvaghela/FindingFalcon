package com.findingfalcone.feproblem1.data.local.prefs

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

}