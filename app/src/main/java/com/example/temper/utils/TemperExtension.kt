package com.example.temper.utils

import android.app.Activity
import android.graphics.Color
import android.net.NetworkCapabilities
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andrognito.flashbar.Flashbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

fun Activity.snackbarError(message: String?){
    var msg = "Something went wrong."
    if (message != null){ msg = message }
    Flashbar.Builder(this)
        .gravity(Flashbar.Gravity.TOP)
        .title("\nError")
        .message(msg)
        .backgroundColor(Color.RED)
        .duration(2000)
        .build()
        .show()
}

fun Activity.snackbarSuccess(message: String?){
    var msg = "Success."
    if (message != null){ msg = message }
    Flashbar.Builder(this)
        .gravity(Flashbar.Gravity.TOP)
        .title("\nSuccess")
        .message(msg)
        .backgroundColor(Color.parseColor("#32CD32"))
        .duration(2000)
        .build()
        .show()
}

fun Activity.snackbarWarning(message: String?){
    var msg = "N/A."
    if (message != null){ msg = message }
    Flashbar.Builder(this)
        .gravity(Flashbar.Gravity.TOP)
        .title("\nWhoops")
        .message(msg)
        .backgroundColor(Color.parseColor("#ff8500"))
        .duration(2000)
        .build()
        .show()
}

fun Activity.snackbarWaiting(message: String?){
    var msg = "N/A."
    if (message != null){ msg = message }
    Flashbar.Builder(this)
        .gravity(Flashbar.Gravity.TOP)
        .title("\nPlease Wait...")
        .message(msg)
        .backgroundColor(Color.parseColor("#636e72"))
        .duration(2000)
        .build()
        .show()
}

fun Date.converted(format: String = "yyyy-MM-dd", locale: Locale = Locale.getDefault()): String {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.format(this)
    } catch (e: Exception){
        "N/A"
    }
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun RecyclerView.attachFab(fab : ExtendedFloatingActionButton) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0)
                fab.hide()
            else if (dy < 0)
                fab.show()
        }
    })
}

fun Int.withZero(): String{
    val date = this.toString()
    return if(date.length == 1) "0$date" else date
}

fun NetworkCapabilities.isConnected(): Boolean{
    return this.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            || this.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            || this.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}