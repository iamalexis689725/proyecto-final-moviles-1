package com.example.appmovilespedidosyachofer.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovilespedidosyachofer.databinding.FragmentListaPedidosFreeBinding
import com.example.appmovilespedidosyachofer.models.PedidoFree
import com.example.appmovilespedidosyachofer.ui.adapters.ListaPedidoSinAsignarAdapter
import com.example.appmovilespedidosyachofer.ui.viewmodels.PedidoViewModel

class ListaPedidosFreeFragment : Fragment(), ListaPedidoSinAsignarAdapter.PedidoSinAsinarItemListener {

    private val viewModel: PedidoViewModel by viewModels()
    private var _binding: FragmentListaPedidosFreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPedidosFreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModelObservers()

        val sharedPreference = requireActivity().getSharedPreferences("APP_PREFS", MODE_PRIVATE)
        val token = sharedPreference.getString("ACCESS_TOKEN", "") ?: ""
        if (token.isNotEmpty()) {
            viewModel.obtenerListaPedidosSinAsignar(token)
        } else {
            Toast.makeText(requireContext(), "No se pudo obtener el token", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.listaPedidoSinAsignar.observe(viewLifecycleOwner) { pedidos ->
            val adapter = binding.rvListaPedidosFree.adapter as ListaPedidoSinAsignarAdapter
            adapter.updateData(pedidos)
        }
    }

    private fun setupRecyclerView() {
        val adapter = ListaPedidoSinAsignarAdapter(this)
        binding.rvListaPedidosFree.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onPedidoSinAsinarItemClicked(pedido: PedidoFree) {
        println(pedido.restaurant_id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

