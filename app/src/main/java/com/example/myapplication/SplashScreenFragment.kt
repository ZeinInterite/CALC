package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class SplashScreenFragment : Fragment() {

    private var imageResource: Int? = null

    companion object {
        fun newInstance(imageResource: Int): SplashScreenFragment {
            val fragment = SplashScreenFragment()
            fragment.imageResource = imageResource
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(imageResource!!)
        return view
    }
}