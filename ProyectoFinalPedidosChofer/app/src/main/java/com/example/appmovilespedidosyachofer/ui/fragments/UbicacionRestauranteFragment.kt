package com.example.appmovilespedidosyachofer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appmovilespedidosyachofer.R

class UbicacionRestauranteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubicacion_restaurante, container, false)
    }

    companion object {
        @JvmStatic fun newInstance() =
                UbicacionRestauranteFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}