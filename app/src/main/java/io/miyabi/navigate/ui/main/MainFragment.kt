package io.miyabi.navigate.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import io.miyabi.navigate.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        val listButton = view.findViewById<Button>(R.id.list_button)
        listButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.listFragment)
        }
        val imageButton = view.findViewById<Button>(R.id.image_button)
        imageButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.imageFragment)
        }
        return view
    }

}
