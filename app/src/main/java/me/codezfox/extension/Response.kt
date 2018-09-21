package me.codezfox.extension

import com.github.kittinunf.result.Result
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext


typealias Response<K> = Deferred<Result<K, Exception>>

fun launchUI(
    context: CoroutineContext = DefaultDispatcher,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    parent: Job? = null,
    onCompletion: CompletionHandler? = null,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(UI, start, parent, onCompletion, block)
}

inline fun <T : Any> asyncR(crossinline callback: () -> T): Response<T> {
    return async(CommonPool) {
        Result.of {
            callback.invoke()
        }
    }
}

suspend inline fun <V : Any> Response<V>.awaitFold(
    success: CallBackK<V>,
    failure: CallBackK<Exception>
) {
    return this.await().fold(success, failure)
}

