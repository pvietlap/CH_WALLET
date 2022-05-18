package com.bautoidem.chwallet.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {
    fun bindImage(context: Context,imageView: ImageView, url: String?) {
        Glide.with(context).load(url).into(imageView)
    }
}