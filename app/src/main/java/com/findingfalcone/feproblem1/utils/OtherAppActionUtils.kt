package com.findingfalcone.feproblem1.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.findingfalcone.feproblem1.utils.log.LogUtils.displayLog

class OtherAppActionUtils {
    var TAG = javaClass.simpleName

    // Method to share either text or URL.
    fun shareTextUrl(mContext: Context, title: String?, subject: String?, url_messageText: String) {
        displayLog(TAG, "shareTextUrl: url: $url_messageText")
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, url_messageText)
        mContext.startActivity(Intent.createChooser(sharingIntent, title))
    }

    fun openBrowserLink(mContext: Context, url: String?) {

        //Open browser
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        mContext.startActivity(browserIntent)
    }
}