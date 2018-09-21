//package me.codezfox.errors
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.support.v4.app.Fragment
//import android.view.View
//import com.androidacademy.hackathonapp.R
//import com.arellomobile.mvp.MvpView
//import com.arellomobile.mvp.viewstate.MvpViewState
//import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
//import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
//import com.balinasoft.haraba.R
//import com.balinasoft.haraba.mvp.auth.SignInActivity
//import me.codezfox.errors.ErrorCause.Companion.ERROR_SILENT
//import me.codezfox.extension.toast
//import org.acra.ACRA
//import org.jetbrains.anko.clearTask
//import org.jetbrains.anko.clearTop
//import org.jetbrains.anko.newTask
//import org.jetbrains.anko.toast
//import retrofit2.HttpException
//import java.net.ConnectException
//import java.net.SocketTimeoutException
//import java.net.UnknownHostException
//
//class ErrorCause : Exception {
//
//    var messageResId: Int? = null
//
//    var httpCode: Int? = null
//        private set
//
//    var isConnectException = false
//
//    companion object {
//
//        const val ERROR_UNAUTHORIZED = "ERROR_UNAUTHORIZED"
//        const val ERROR_SILENT = "ERROR_SILENT"
//        const val ERROR_USER_VERIFICATION = "ERROR_USER_VERIFICATION"
//
//        fun createErrorCause(e: Throwable): ErrorCause = when (e) {
//            is ErrorCause -> e
//            is HttpException -> HttpErrorResponseParser().parse(e)
//            is SocketTimeoutException -> ErrorCause(R.string.error_unknown_host_exception).also {
//                it.isConnectException = true
//            }
//            is UnknownHostException -> ErrorCause(R.string.error_unknown_host_exception).also {
//                it.isConnectException = true
//            }
//            is ConnectException -> ErrorCause(R.string.error_unknown_host_exception).also {
//                it.isConnectException = true
//            }
//            else -> ErrorCause(R.string.error_unknown)
//        }
//
//    }
//
//    fun isUnauthorized() = message == ERROR_UNAUTHORIZED
//
//    constructor(message: String) : super(message)
//
//    constructor(message: String, httpException: HttpException) : super(message, httpException) {
//        this.httpCode = httpException.code()
//    }
//
//    constructor(httpException: HttpException) : super(httpException) {
//        this.httpCode = httpException.code()
//    }
//
//    constructor(messageResId: Int) {
//        this.messageResId = messageResId
//    }
//
//    fun getErrorCause(context: Context): String {
//        val errorMessage = message
//
//        if (errorMessage != null) {
//            return errorMessage
//        }
//
//        return context.getString(messageResId!!)
//    }
//
//}
//
//@StateStrategyType(OneExecutionStateStrategy::class)
//fun MvpView.showMessage(errorCause: ErrorCause) {
//
//    if (errorCause.message == ERROR_SILENT) {
//        return
//    }
//
//    val view = (this as MvpViewState<*>).views.find { it is Context || it is Fragment }
//
//    if (errorCause.message == ErrorCause.ERROR_UNAUTHORIZED) {
//        when (view) {
//            is Activity -> {
//                //todo loginactivity
//                view.startActivity(Intent(view, SignInActivity::class.java).clearTask().clearTop().newTask())
//                view.finish()
//                return
//            }
//            is Fragment -> {
//                //todo loginactivity
//                view.activity!!.startActivity(Intent(view.activity, SignInActivity::class.java).clearTask().clearTop().newTask())
//                view.activity!!.finish()
//                return
//            }
//        }
//    }
//
//    when (view) {
//        is Context -> view.toast(errorCause.getErrorCause(view))
//        is Fragment -> view.toast(errorCause.getErrorCause(view.context!!))
//    }
//}
//
//fun MvpView.getContext(): Context? {
//    return when (this) {
//        is Activity -> this
//        is Fragment -> this.activity
//        else -> null
//    }
//}
//
//fun <T : View> MvpView.getLayout(id: Int): T? {
//    return when (this) {
//        is Activity -> findViewById<T>(id)
//        is Fragment -> view?.findViewById(id)
//        else -> null
//    }
//}
//
//@StateStrategyType(OneExecutionStateStrategy::class)
//fun MvpView.showMessage(e: Throwable) {
//
//    e.printStackTrace()
//
////    FirebaseCrash.report(e)
////    Crashlytics.logException(e)
//
//    val errorMessage = when (e) {
//        is ErrorCause -> e
//        is HttpException -> {
//            ACRA.getErrorReporter().handleSilentException(e)
//            HttpErrorResponseParser().parse(e)
//        }
//        is SocketTimeoutException -> ErrorCause(R.string.error_socket_timeout_exception)
//        is UnknownHostException -> ErrorCause(R.string.error_unknown_host_exception)
//        is ConnectException -> ErrorCause(R.string.error_unknown_host_exception)
//        else -> {
//            ACRA.getErrorReporter().handleSilentException(e)
//            val s = e.localizedMessage ?: e.message
//            if (s != null) {
//                ErrorCause(s)
//            } else {
//                ErrorCause(R.string.error_unknown)
//            }
//        }
//    }
//
//    this.showMessage(errorMessage)
//}
