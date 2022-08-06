package com.zj.calculator.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

private const val TAG = "ToastUtils"
private var toast: Toast? = null

fun Context?.showToast(content: String?) {
    if (this == null) {
        Log.e(TAG, "showToast: context is null")
        return
    }
    if (toast == null) {
        toast = Toast.makeText(this, content, Toast.LENGTH_SHORT)
    } else {
        toast?.setText(content)
    }
    toast?.show()
}

fun Context?.showToast(resId: Int) {
    if (this == null) {
        Log.e(TAG, "showToast: context is null")
        return
    }
    if (toast == null) {
        toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT)
    } else {
        toast?.setText(resId)
    }
    toast?.show()
}