package com.bautoidem.chwallet.utils

import android.util.Base64
import android.view.View

fun View.toGone() = apply {
    visibility = View.GONE
}

fun View.toVisible() = apply {
    visibility = View.VISIBLE
}

fun String.decode(): String {
    return Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))
}

fun String.encode(): String {
    return Base64.encodeToString(this.toByteArray(charset("UTF-8")), Base64.DEFAULT)
}
