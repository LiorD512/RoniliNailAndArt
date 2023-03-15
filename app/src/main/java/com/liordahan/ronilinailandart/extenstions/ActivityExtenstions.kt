package com.liordahan.ronilinailandart.extenstions

import android.app.Activity
import android.os.Build
import android.view.WindowManager

fun Activity.applyFullScreen(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
    } else {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    window.statusBarColor = resources.getColor(android.R.color.transparent, null)
}