package io.miyabi.navigate.ui.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import io.miyabi.navigate.R
import io.miyabi.navigate.task.ClipDrawableTask
import io.miyabi.navigate.ui.loadImage
import io.miyabi.navigate.ui.visible

class CompareLayout : RelativeLayout, ClipDrawableTask.AfterImage {

    private var beforeImageView : ImageView
    private var afterImageView : ImageView
    private var seekBar : SeekBar
    private var beforeTextView : TextView
    private var afterTextView: TextView

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val attr = context?.theme?.obtainStyledAttributes(attrs, R.styleable.BeforeAfterSlider,0,0)
        attr?.let {
            try {
                putThumb(it.getDrawable(R.styleable.BeforeAfterSlider_slider_thumb))
                putBefore(it.getDrawable(R.styleable.BeforeAfterSlider_before_image))
                putAfter(it.getDrawable(R.styleable.BeforeAfterSlider_after_image))
            } finally {
                it.recycle()
            }
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.compare_layout, this)
        beforeImageView = findViewById(R.id.before_image)
        afterImageView = findViewById(R.id.after_image)
        seekBar = findViewById(R.id.seekbar)
        beforeTextView = findViewById(R.id.before_text)
        afterTextView = findViewById(R.id.after_text)
    }

    override fun onLoaded(success: Boolean) {
        seekBar.visible(success)
    }

    private val afterHide = 1000

    override fun textVisible(progress: Int) {
        afterTextView.visibility = if (afterHide < progress) View.VISIBLE else View.GONE
    }

    fun putBefore(uri: String): CompareLayout {
        beforeImageView.loadImage(uri)
        return this
    }

    fun putBefore(drawable: Drawable?): CompareLayout {
        beforeImageView.loadImage(drawable)
        return this
    }

    fun putAfter(uri: String) {
        if (uri.isNotBlank()) {
            val task = ClipDrawableTask<String>(afterImageView, seekBar, context, this)
            task.execute(uri)
        }
    }

    fun putAfter(drawable: Drawable?) {
        drawable?.let {
            val task = ClipDrawableTask<Drawable>(afterImageView, seekBar, context, this)
            task.execute(it)
        }
    }

    fun putThumb(thumb: Drawable?){
        thumb?.let {
            seekBar.thumb = it
        }
    }

}