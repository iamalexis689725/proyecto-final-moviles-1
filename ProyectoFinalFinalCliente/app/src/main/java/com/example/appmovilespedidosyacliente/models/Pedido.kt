package com.example.appmovilespedidosyacliente.models

data class Pedido(
    val restaurant_id: Int,
    val total: Double,
    val address: String,
    val latitude: String,
    val longitude: String,
    val details: List<DetallePedido>
)