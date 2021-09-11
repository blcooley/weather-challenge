package com.example.androidchallenge.extension

import android.widget.ImageView
import com.example.androidchallenge.R
import com.squareup.picasso.Picasso

fun ImageView.loadIcon(iconString: String) {
    val path = context.getString(R.string.icon_url, iconString)
    Picasso.get().load(path).into(this)
}
