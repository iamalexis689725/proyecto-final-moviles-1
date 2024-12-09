package com.example.appmovilespedidosyachofer.models

data class PedidoFree (
    val id: Int,
    val user_id: Int,
    val restaurant_id: Int,
    val total: String,
    val latitude: String,
    val longitude: String,
    val address: String,
    val driver_id: Int,
    val status: String
)
typealias misPedidosFree = ArrayList<PedidoFree>