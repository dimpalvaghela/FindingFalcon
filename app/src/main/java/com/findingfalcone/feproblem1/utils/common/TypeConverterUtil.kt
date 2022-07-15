package com.findingfalcone.feproblem1.utils.common

import androidx.room.TypeConverter

object TypeConverterUtil {

    fun fromIntToBoolean(value: Int?): Boolean {
        return value != 0
    }

    @TypeConverter
    fun fromBooleanToInt(value: Boolean?): Int {
        return if(value == true){
            1
        }else{
            0
        }
    }
}