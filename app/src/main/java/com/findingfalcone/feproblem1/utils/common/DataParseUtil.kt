package com.findingfalcone.feproblem1.utils.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object DataParseUtil {

    fun arrayToString(arrayList: ArrayList<String?>) : String{
        try {
            val gson = Gson()

            val inputString = gson.toJson(arrayList)

            println("inputString= $inputString")

            return inputString
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun stringToArray(string: String) : ArrayList<String>{
        try {
            val gson = Gson()
            val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type

            val finalOutputString: ArrayList<String> = gson.fromJson(string, type)

            return finalOutputString
        }catch (e:Exception){
            e.printStackTrace()
        }
        return arrayListOf()
    }

    //Check - Is null?
    fun getStringValue(name: String?): String {
        var str = ""
        if(name != null){
            str = name
        }
        return str
    }

    //Check - Is null?
    fun nullToBlank(str: String?): String? {
        var str = str
        if (str == null) {
            str = ""
        } else if (str.equals("null", ignoreCase = true)) {
            str = ""
        }
        return str
    }
}