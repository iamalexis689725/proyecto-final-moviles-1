package com.example.appmovilespedidosyacliente.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovilespedidosyacliente.models.Pedido
import com.example.appmovilespedidosyacliente.models.PedidosDelUsuarios
import com.example.appmovilespedidosyacliente.repositories.PedidoRepository

class PedidoViewModel : ViewModel() {

    private val _listaPedido = MutableLiveData<PedidosDelUsuarios>().apply {
        value = arrayListOf()
    }

    val listaPedido = _listaPedido

    fun obtenerListaPedidos(token: String) {
        PedidoRepository.verPedidosDelUsuariosUsuario(
            token,
            onSuccess = {
                _listaPedido.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun crearPedido(
        token: String?,
        pedido: Pedido,
        onSuccess: (Pedido) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        token?.let {
            PedidoRepository.crearPedido(
                token = it,
                pedido = pedido,
                onSuccess = { pedidoCreado ->
                    onSuccess(pedidoCreado)
                },
                onError = { error ->
                    onError(error)
                }
            )
        } ?: onError(Throwable("Autenticación no encontrada, por favor inicie sesión de nuevo."))
    }


}