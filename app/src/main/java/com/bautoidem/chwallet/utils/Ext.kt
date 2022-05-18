package com.bautoidem.chwallet.utils

import android.view.View

fun View.toGone() = apply {
    visibility = View.GONE
}

fun View.toVisible() = apply {
    visibility = View.VISIBLE
}
