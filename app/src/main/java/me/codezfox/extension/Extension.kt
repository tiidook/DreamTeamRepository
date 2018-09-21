package me.codezfox.extension

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.res.Resources
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.androidacademy.hackathonapp.R


//@Suppress("UNCHECKED_CAST")
//fun <T> Activity.findView(localId: Int): Lazy<T> = lazy { this.findViewById(localId) as T }
//
//@Suppress("UNCHECKED_CAST")
//fun <T> View.findView(localId: Int): Lazy<T> = lazy { this.findViewById(localId) as T }
//
//@Suppress("UNCHECKED_CAST")
//fun <T> Fragment.findView(localId: Int): Lazy<T> = lazy { this.view!!.findViewById(localId) as T }

typealias CallBackK<T> = (T) -> Unit
typealias CallBackKUnit = () -> Unit

fun Fragment.setupUI(view: View) {
    activity?.setupUI(view)
}

fun Activity.setupUI(view: View? = this.window.decorView.rootView) {

    if (view != null && !(view is TextView || view is ImageView)) {
        view.requestFocus()
        view.setOnTouchListener { v, _ ->
            v.requestFocus()
            hideSoftKeyboard()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0..view.childCount - 1) {
            val innerView = view.getChildAt(i)
            setupUI(innerView)
        }
    }

    view?.requestFocus()

}

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    if (view != null) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Context.showSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}


/**
 * Convert dp to pixel
 */
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Convert dp to pixel
 */
val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)


fun SwipeRefreshLayout.setRefresh(refresh: Boolean) {
    if (refresh) {
        this.isRefreshing = refresh
    } else {
        this.post { this.isRefreshing = refresh }
    }
}

fun Resources.Theme.isWindowActionBar(): Boolean {
    val typedValue = TypedValue()
    this.resolveAttribute(R.attr.windowActionBar, typedValue, true)
    return typedValue.data == 0
}