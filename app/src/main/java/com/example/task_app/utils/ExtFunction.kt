package com.example.task_app.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController

fun NavController.navigateFragment(actionId: Int) {
    this.navigate(actionId)
}

fun Context.showToast(str: String) {
    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE

}
fun View.onSingleClick(debounceTime: Long = 1200L, action: ((View)) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(this@onSingleClick)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
