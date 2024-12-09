package com.example.appmovilespedidosyacliente.models

data class Restaurante (
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val logo: String,
    val products: ArrayList<Producto> = arrayListOf(),

)
typealias Restaurantes = ArrayList<Restaurante>