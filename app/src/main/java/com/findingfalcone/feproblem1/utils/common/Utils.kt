package com.findingfalcone.feproblem1.utils.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.findingfalcone.feproblem1.utils.log.LogUtils
import java.util.*

/*
Util has
  - Date picker dialog and keyboard helper
 */

object Utils {


    fun disableEnableView(enable: Boolean, vg: ViewGroup) {
        for (i in 0 until vg.childCount) {
            val child: View = vg.getChildAt(i)
            child.isEnabled = enable
            if (child is ViewGroup) {
                disableEnableView(enable, child)
            }
        }
    }

    fun hideKeyboard(context: Context) {
        try {
            LogUtils.displayLog("Utils", "Util keyboard hideKeyboard Context context")
            (context as Activity).window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            if (context.currentFocus != null && context.currentFocus!!
                    .windowToken != null
            ) (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                context.currentFocus!!.windowToken, 0
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}