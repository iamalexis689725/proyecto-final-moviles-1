package com.example.appmovilespedidosyachofer.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.appmovilespedidosyachofer.repositories.UsuarioRepository
import com.example.appmovilespedidosyacliente.models.Usuario


class UsuarioViewModel: ViewModel() {

    fun crearUsuario(usuario: Usuario, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        UsuarioRepository.postCrearUsuario(
            usuario,
            onSuccess = {
                onSuccess()
            },
            onError = {
                onError(it)
            }
        )
    }

}