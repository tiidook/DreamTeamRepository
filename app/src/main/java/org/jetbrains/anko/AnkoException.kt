/*
 * Copyright 2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.anko

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Build

open class AnkoException(message: String = "") : RuntimeException(message)

/**
 * Indicates that the called property does not have a getter.
 * Some of the extension properties only have a getter, because Android SDK does not provide a
 *   method to get the proper value. Another case is when the getter is senseless.
 *
 * Example: there is a [View.padding] extension property, which is used to set the equal padding value
 *   for all paddings (left, top, right, bottom). Technically, the left padding is unrelated to,
 *   for example, the bottom padding, so there is just no right value to return.
 *
 * @param name the property name
 *
 * @see [View.padding] extension property
 */
class PropertyWithoutGetterException(name: String) : AnkoException("'$name' property does not have a getter")

/**
 * Return the grayscale color with the zero opacity using the single color value.
 * E.g., 0xC0 will be translated to 0xC0C0C0.
 */
val Int.gray: Int
    get() = this or (this shl 8) or (this shl 16)

/**
 * Return the color with 0xFF opacity.
 * E.g., 0xabcdef will be translated to 0xFFabcdef.
 */
val Int.opaque: Int
    get() = this or 0xff000000.toInt()

/**
 * Return the color with the given alpha value.
 * Examples:
 *   0xabcdef.withAlpha(0xCF) == 0xCFabcdef
 *   0xFFabcdef.withAlpha(0xCF) == 0xCFabcdef
 *
 * @param alpha the alpha channel value: [0x0..0xFF].
 * @return the color with the given alpha value applied.
 */
fun Int.withAlpha(alpha: Int): Int {
    require(alpha >= 0 && alpha <= 0xFF)
    return this and 0x00FFFFFF or (alpha shl 24)
}

enum class ScreenSize {
    SMALL,
    NORMAL,
    LARGE,
    XLARGE
}

enum class UiMode {
    NORMAL,
    CAR,
    DESK,
    TELEVISION,
    APPLIANCE,
    WATCH
}

enum class Orientation {
    PORTRAIT,
    LANDSCAPE,
    SQUARE
}

/**
 * Execute [f] if the device configuration matches all given constraints.
 * You can use named arguments to provide only the relevant constraints.
 * All null constraints are ignored.
 *
 * @param screenSize the required screen size.
 * @param density the required screen density.
 * @param language the required system language.
 * @param orientation the current screen orientation.
 * @param long true, if the screen layout is long. See [Configuration.SCREENLAYOUT_LONG_YES] for more information.
 * @param fromSdk the minimal SDK version for code to execute.
 * @param sdk the target SDK version. Code will not be executed if the device Android SDK version is different
 *        (lower or higher than the given value).
 * @param uiMode the required interface mode.
 * @param nightMode true, if the device should be in the night mode, false if should not.
 * @param rightToLeft true, if the device locale should be a right-to-left one, false if should not.
 * @param smallestWidth the required smallest width of the screen.
 */
inline fun <T: Any> Context.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        f: () -> T
): T? = if (AnkoInternals.testConfiguration(this, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth)) f() else null

/**
 * Execute [f] if the device configuration matches all given constraints.
 * You can use named arguments to provide only the relevant constraints.
 * All null constraints are ignored.
 *
 * @param screenSize the required screen size.
 * @param density the required screen density.
 * @param language the required system language.
 * @param orientation the current screen orientation.
 * @param long true, if the screen layout is long. See [Configuration.SCREENLAYOUT_LONG_YES] for more information.
 * @param fromSdk the minimal SDK version for code to execute.
 * @param sdk the target SDK version. Code will not be executed if the device Android SDK version is different
 *        (lower or higher than the given value).
 * @param uiMode the required interface mode.
 * @param nightMode true, if the device should be in the night mode, false if should not.
 * @param rightToLeft true, if the device locale should be a right-to-left one, false if should not.
 * @param smallestWidth the required smallest width of the screen.
 */
inline fun <T: Any> Activity.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        f: () -> T
): T? = if (AnkoInternals.testConfiguration(this, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth)) f() else null

/**
 * Execute [f] if the device configuration matches all given constraints.
 * You can use named arguments to provide only the relevant constraints.
 * All null constraints are ignored.
 *
 * @param screenSize the required screen size.
 * @param density the required screen density.
 * @param language the required system language.
 * @param orientation the current screen orientation.
 * @param long true, if the screen layout is long. See [Configuration.SCREENLAYOUT_LONG_YES] for more information.
 * @param fromSdk the minimal SDK version for code to execute.
 * @param sdk the target SDK version. Code will not be executed if the device Android SDK version is different
 *        (lower or higher than the given value).
 * @param uiMode the required interface mode.
 * @param nightMode true, if the device should be in the night mode, false if should not.
 * @param rightToLeft true, if the device locale should be a right-to-left one, false if should not.
 * @param smallestWidth the required smallest width of the screen.
 */
inline fun <T: Any> AnkoContext<*>.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        f: () -> T
): T? = if (AnkoInternals.testConfiguration(ctx, screenSize, density, language, orientation, long,
        fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth)) f() else null

/**
 * Execute [f] if the device configuration matches all given constraints.
 * You can use named arguments to provide only the relevant constraints.
 * All null constraints are ignored.
 *
 * @param screenSize the required screen size.
 * @param density the required screen density.
 * @param language the required system language.
 * @param orientation the current screen orientation.
 * @param long true, if the screen layout is long. See [Configuration.SCREENLAYOUT_LONG_YES] for more information.
 * @param fromSdk the minimal SDK version for code to execute.
 * @param sdk the target SDK version. Code will not be executed if the device Android SDK version is different
 *        (lower or higher than the given value).
 * @param uiMode the required interface mode.
 * @param nightMode true, if the device should be in the night mode, false if should not.
 * @param rightToLeft true, if the device locale should be a right-to-left one, false if should not.
 * @param smallestWidth the required smallest width of the screen.
 */
inline fun <T: Any> Fragment.configuration(
        screenSize: ScreenSize? = null,
        density: ClosedRange<Int>? = null,
        language: String? = null,
        orientation: Orientation? = null,
        long: Boolean? = null,
        fromSdk: Int? = null,
        sdk: Int? = null,
        uiMode: UiMode? = null,
        nightMode: Boolean? = null,
        rightToLeft: Boolean? = null,
        smallestWidth: Int? = null,
        f: () -> T
): T? {
    val act = activity
    return if (act != null) {
        if (AnkoInternals.testConfiguration(act, screenSize, density, language, orientation, long,
                fromSdk, sdk, uiMode, nightMode, rightToLeft, smallestWidth)) f() else null
    }
    else null
}

/**
 * Execute [f] inly if the current Android SDK version is [version] or newer.
 * Do nothing otherwise.
 */
inline fun doFromSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT >= version) f()
}

/**
 * Execute [f] only if the current Android SDK version is [version].
 * Do nothing otherwise.
 */
inline fun doIfSdk(version: Int, f: () -> Unit) {
    if (Build.VERSION.SDK_INT == version) f()
}

/**
 * Result of the [attempt] function.
 * Either [value] or [error] is not null.
 *
 * @property value the return value if code execution was finished without an exception, null otherwise.
 * @property error a caught [Throwable] or null if nothing was caught.
 */
data class AttemptResult<T>(val value: T?, val error: Throwable?)

/**
 * Execute [f] and return the result or an exception, if an exception was occurred.
 */
fun <T> attempt(f: () -> T): AttemptResult<T> {
    var value: T? = null
    var error: Throwable? = null
    try {
        value = f()
    } catch(t: Throwable) {
        error = t
    }
    return AttemptResult(value, error)
}