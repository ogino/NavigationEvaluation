package io.miyabi.navigate.task

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ClipDrawable
import android.os.AsyncTask
import android.os.Looper
import android.view.Gravity
import android.widget.ImageView
import android.widget.SeekBar
import com.squareup.picasso.Picasso
import io.miyabi.navigate.ui.scaledBitmap
import java.lang.ref.WeakReference

class ClipDrawableTask<T>(
    imageView: ImageView,
    seekBar: SeekBar,
    private val context: Context,
    private val listener: AfterImage? = null
) : AsyncTask<T, Void, ClipDrawable>() {

    private val imageRef = WeakReference(imageView)
    private val seekBarRef = WeakReference(seekBar)

    override fun doInBackground(vararg params: T): ClipDrawable {
        Looper.myLooper()?.let {
            Looper.prepare()
        }
        try {
            var bitmap =
                if (params[0] is String) Picasso.get().load(params[0].toString()).resize(0, imageHeight()).onlyScaleDown().get()
                else (params[0] as BitmapDrawable).bitmap
            bitmap?.scaledBitmap()?.let {
                bitmap = it
            }
            return ClipDrawable(
                BitmapDrawable(context.resources, bitmap),
                Gravity.START,
                ClipDrawable.HORIZONTAL
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ClipDrawable(null, 0, 0)
    }

    private val progressNum = 5000

    override fun onPostExecute(clipDrawable: ClipDrawable?) {
        imageRef.get()?.let { view ->
            clipDrawable?.let {
                initSeekBar(it)
                view.setImageDrawable(it)
                when (it.level) {
                    0 -> it.level = seekBarRef.get()!!.progress
                    else -> it.level = progressNum
                }
                listener?.onLoaded(true)
                return
            }
        }
        listener?.onLoaded(false)
    }

    private fun initSeekBar(clipDrawable: ClipDrawable) {
        seekBarRef.get()!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                clipDrawable.level = progress
                listener?.textVisible(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    private fun imageHeight(): Int {
        var height = 0
        imageRef.get()?.let {
            while (height < 1)
                height = it.height
        }
        return if (0 < height) height else 1
    }

    interface AfterImage {
        fun onLoaded(success: Boolean)
        fun textVisible(progress: Int)
    }

}