package com.example.appmovilespedidosyachofer.models

data class Restaurante (
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val logo: String,
)
typealias Restaurantes = ArrayList<Restaurante>