//package me.codezfox.moxy
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.support.annotation.DrawableRes
//import android.view.MenuItem
//import android.widget.TextView
//import com.androidacademy.hackathonapp.R
//import com.arellomobile.mvp.MvpAppCompatActivity
//import com.balinasoft.haraba.R
//import me.codezfox.extension.isWindowActionBar
//
//
//@SuppressLint("Registered")
//open class BaseActivity : MvpAppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        title = ""
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> onHomePressed()
//            else -> return false
//        }
//        return true
//    }
//
//    protected open fun onHomePressed() {
//        onBackPressed()
//    }
//
//    protected fun setupHomeButton() {
//
//        if (theme.isWindowActionBar()) {
//            setSupportActionBar(findViewById(R.id.toolbar))
//        }
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        //        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
//    }
//
//    protected fun setupIconToolbar(@DrawableRes id: Int) {
//        supportActionBar?.setHomeAsUpIndicator(id)
//    }
//
//    protected fun disableShadowToolbar() {
//        supportActionBar?.elevation = 0f
//    }
//
//    override fun setTitle(titleId: Int) {
//        val customTitleToolbar: TextView? = findViewById(R.id.customTitleToolbar)
//        if (customTitleToolbar == null) {
//            super.setTitle(titleId)
//        } else {
//            customTitleToolbar.setText(titleId)
//        }
//    }
//
//    override fun setTitle(titleString: CharSequence) {
//        val customTitleToolbar: TextView? = findViewById(R.id.customTitleToolbar)
//        if (customTitleToolbar == null) {
//            super.setTitle(titleString)
//        } else {
//            customTitleToolbar.text = titleString
//        }
//    }
//
//}
