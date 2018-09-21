package me.codezfox.extension

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager


fun Context.isNetworkConnected() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null

fun Context.isIntentAvailable(action: String): Boolean {
    return this.packageManager.queryIntentActivities(Intent(action), PackageManager.MATCH_DEFAULT_ONLY).size > 0
}

val Context.notificationManager: NotificationManager get() = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
