
@file:Suppress("NOTHING_TO_INLINE")
package me.codezfox.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import org.jetbrains.anko.internals.AnkoInternals

//inline fun <reified T: Any> Fragment.intentFor(vararg params: Pair<String, Any>): Intent {
//    return AnkoInternals.createIntent(activity, T::class.java, params)
//}
//
//inline fun <reified T: Activity> Fragment.startActivity(vararg params: Pair<String, Any>) {
//    AnkoInternals.internalStartActivity(activity, T::class.java, params)
//}

inline fun <reified T: Activity> Context.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivity(this, T::class.java, params)
}