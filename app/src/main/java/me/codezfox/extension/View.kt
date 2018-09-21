package me.codezfox.extension

import android.view.View

fun View.onClick(l: (v: View?) -> Unit) {
    setOnClickListener(l)
}

fun View.onLongClick(l: (v: android.view.View?) -> Boolean) {
    setOnLongClickListener(l)
}

fun View.visibleOrGone(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun View.visibleOrInvisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}