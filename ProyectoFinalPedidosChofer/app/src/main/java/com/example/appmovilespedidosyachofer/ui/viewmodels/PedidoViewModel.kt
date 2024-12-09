package com.example.appmovilespedidosyachofer.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovilespedidosyachofer.models.misPedidosFree
import com.example.appmovilespedidosyachofer.repositories.PedidoRepository

class PedidoViewModel: ViewModel() {
    private val _listaPedidoSinAsignar = MutableLiveData<misPedidosFree>().apply {
        value = arrayListOf()
    }

    val listaPedidoSinAsignar = _listaPedidoSinAsignar

    fun obtenerListaPedidosSinAsignar(token: String) {
        PedidoRepository.verPedidosSinAsignar(
            token,
            onSuccess = {
                _listaPedidoSinAsignar.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }


}