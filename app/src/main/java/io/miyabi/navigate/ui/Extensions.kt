package io.miyabi.navigate.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?){
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadImage(drawable: Drawable?){
    this.setImageDrawable(drawable)
}

fun View.visible(visible: Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Bitmap.scaledBitmap(): Bitmap? {
    try {
        if (this.width > 0 && this.height > 0)
            return Bitmap.createScaledBitmap(this, this.width, this.height, false)
        return this
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}