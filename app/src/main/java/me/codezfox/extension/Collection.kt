package me.codezfox.extension


/** Returns `true` if the collection is null or empty. */
inline fun <T> Collection<T>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

fun <T> MutableList<T>.set(element: T) {
    val indexOf = this.indexOf(element)
    if (indexOf >= 0) {
        this[indexOf] = element
    } else {
        this.add(element)
    }
}