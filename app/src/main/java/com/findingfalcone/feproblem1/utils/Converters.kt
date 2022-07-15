package com.findingfalcone.feproblem1.utils

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.sql.Date

object Converters {

    val TAG = javaClass.name





    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    fun <T> objectArrayToString(mList: ArrayList<T>) : String {
        //LogUtils.displayLog("Shared preference"," mList "+mList);
        if (mList != null && mList.size > 0) {
            val gson = Gson()
            val json = gson.toJson(mList)
            // This line is IMPORTANT !!!
            return json
        }

        return "";
    }

    fun <T> stringToObjectArray(context: Context?, json: String?): ArrayList<T?>? {
        var arrayList: ArrayList<T?>? = ArrayList()
        try {
            val gson = Gson()
            val type = object : TypeToken<ArrayList<T>?>() {}.type
            if (json != null) {
                arrayList = gson.fromJson(json, type)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            arrayList = ArrayList<T?>()
        }
        return arrayList
    }



}