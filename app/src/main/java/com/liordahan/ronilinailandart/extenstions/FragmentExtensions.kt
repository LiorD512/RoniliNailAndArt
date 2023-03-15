package com.liordahan.ronilinailandart.extenstions

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showErrorToast(title: String?, length: Int = Toast.LENGTH_SHORT) {
    val toast = Toast(requireContext())
    toast.setText(title)
    toast.duration = length
    toast.show()
}

