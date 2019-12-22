package io.miyabi.navigate.ui.image

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import io.miyabi.navigate.R

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.image_frament, container, false)
        val beforeURL = "https://www.publicdomainpictures.net/pictures/10000/velka/947-1262213425CFHP.jpg"
        val afterURL = "https://www.publicdomainpictures.net/pictures/80000/velka/sunset-from-sandpatch.jpg"
//        val beforeURL = "https://cdn.hasselblad.com/samples/x1d-II-50c/x1d-II-sample-01.jpg"
//        val afterURL = "https://cdn.hasselblad.com/samples/x1d-II-50c/x1d-II-sample-02.jpg"
        val layout = view.findViewById<CompareLayout>(R.id.compare_layout)
        layout.putBefore(beforeURL).putAfter(afterURL)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

    }
}