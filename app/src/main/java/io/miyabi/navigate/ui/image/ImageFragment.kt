package io.miyabi.navigate.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.miyabi.navigate.R

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_frament, container, false)
        val imgUrl1 = "https://cdn.hasselblad.com/samples/x1d-II-50c/x1d-II-sample-01.jpg"
        val imgUrl2 = "https://cdn.hasselblad.com/samples/x1d-II-50c/x1d-II-sample-02.jpg"
        val slider = view.findViewById<CompareSlider>(R.id.compare_slider)
        slider.putBefore(imgUrl1).putAfter(imgUrl2)
        return view
    }
}