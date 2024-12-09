package com.example.appmovilespedidosyacliente.models

data class PedidosDelUsuario (
    val id: Int,
    val user_id: Int,
    val restaurant_id: Int,
    val total: String,
    val latitude: String,
    val longitude: String,
    val address: String,
    val driverID: Any? = null,
    val status: String,
)
typealias PedidosDelUsuarios = ArrayList<PedidosDelUsuario>