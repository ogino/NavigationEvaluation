package io.miyabi.navigate.task

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ClipDrawable
import android.os.AsyncTask
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.SeekBar
import com.bumptech.glide.Glide
import java.lang.ref.WeakReference

class ClipDrawableTask<T>(imageView: ImageView, seekBar: SeekBar, private val context: Context, private val listener: OnAfterImageLoaded? = null) : AsyncTask<T, Void, ClipDrawable>() {

    private val imageRef = WeakReference(imageView)
    private val seekBarRef = WeakReference(seekBar)

    override fun doInBackground(vararg params: T): ClipDrawable {
        Looper.myLooper()?.let { Looper.prepare() }
        try {
            var theBitmap: Bitmap
            if (params[0] is String) {
                theBitmap = Glide.with(context)
                    .asBitmap()
                    .load(params[0])
                    .submit()
                    .get()
            } else {
                theBitmap = (params[0] as BitmapDrawable).bitmap
            }
            getScaledBitmap(theBitmap)?.let { theBitmap = it }
            val bitmapDrawable = BitmapDrawable(context.resources, theBitmap)
            return ClipDrawable(bitmapDrawable, Gravity.START, ClipDrawable.HORIZONTAL)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ClipDrawable(null, 0, 0)
    }

    private fun getScaledBitmap(bitmap: Bitmap): Bitmap? {
        try {
            val image = imageRef.get()
            image?.also {
                val imageWidth = image.width
                val imageHeight = image.height
                if (imageWidth > 0 && imageHeight > 0)
                    return Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false)
            } ?: run {
                return bitmap
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private val progressNum = 5000

    override fun onPostExecute(clipDrawable: ClipDrawable?) {
        val image = imageRef.get()
        image?.let {
            clipDrawable?.let {
                initSeekBar(clipDrawable)
                image.setImageDrawable(clipDrawable)
                when(clipDrawable.level) {
                    0 -> clipDrawable.level = seekBarRef.get()!!.progress
                    else -> clipDrawable.level = progressNum
                }
                listener?.onLoadedFinished(true)
                return
            }
        }
        listener?.onLoadedFinished(false)
    }

    private fun initSeekBar(clipDrawable: ClipDrawable) {
        seekBarRef.get()!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                clipDrawable.level = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    interface OnAfterImageLoaded {
        fun onLoadedFinished(loadedSuccess: Boolean)
    }

}