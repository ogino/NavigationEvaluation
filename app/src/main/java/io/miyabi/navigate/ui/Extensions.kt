package io.miyabi.navigate.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imgUrl: String?){
    Glide.with(context).load(imgUrl).into(this)
}

fun ImageView.loadImage(imgDrawable: Drawable?){
    this.setImageDrawable(imgDrawable)
}

fun View.stayVisibleOrGone(stay: Boolean){
    this.visibility = if (stay) View.VISIBLE else View.GONE
}