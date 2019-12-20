package io.miyabi.navigate.ui.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import io.miyabi.navigate.R
import io.miyabi.navigate.task.ClipDrawableTask
import io.miyabi.navigate.ui.loadImage
import io.miyabi.navigate.ui.stayVisibleOrGone

class CompareSlider : RelativeLayout, ClipDrawableTask.OnAfterImageLoaded {

    private var beforeImageView : ImageView
    private var afterImageView : ImageView
    private var seekBar : SeekBar

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val attr = context?.theme?.obtainStyledAttributes(attrs, R.styleable.BeforeAfterSlider,0,0)
        attr?.let {
            try {
                putThumb(attr.getDrawable(R.styleable.BeforeAfterSlider_slider_thumb))
                putBefore(attr.getDrawable(R.styleable.BeforeAfterSlider_before_image))
                putAfter(attr.getDrawable(R.styleable.BeforeAfterSlider_after_image))
            } finally {
                attr.recycle()
            }
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.compare_layout, this)
        beforeImageView = findViewById<ImageView>(R.id.before_image)
        afterImageView = findViewById<ImageView>(R.id.after_image)
        seekBar = findViewById<SeekBar>(R.id.seekbar)
    }

    override fun onLoadedFinished(loadedSuccess: Boolean) {
        seekBar.stayVisibleOrGone(loadedSuccess)
    }

    fun putBefore(imageUri: String): CompareSlider {
        beforeImageView.loadImage(imageUri)
        return this
    }

    fun putBefore(imgDrawable: Drawable?): CompareSlider {
        beforeImageView.loadImage(imgDrawable)
        return this
    }

    fun putAfter(imageUri: String) {
        if (imageUri.isNotBlank()) {
            val task = ClipDrawableTask<String>(afterImageView, seekBar, context, this)
            task.execute(imageUri)
        }
    }

    fun putAfter(imageDrawable: Drawable?) {
        imageDrawable?.let {
            val task = ClipDrawableTask<Drawable>(afterImageView, seekBar, context, this)
            task.execute(it)
        }
    }

    fun putThumb(thumb: Drawable?){
        thumb?.let {
            seekBar?.thumb = thumb
        }
    }

}