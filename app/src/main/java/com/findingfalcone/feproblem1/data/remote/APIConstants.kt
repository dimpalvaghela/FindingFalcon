package com.findingfalcone.feproblem1.data.remote

object APIConstants {


    const val APP_NAME = "findingfalcone.feproblem1"
    const val TOKEN = "token"


    interface RequestValue{
        companion object {
            val ISSUE_TYPE_MAINTENANCE = "maintenance"
            val ISSUE_STATUS_NEW = "_new"
        }
    }

}