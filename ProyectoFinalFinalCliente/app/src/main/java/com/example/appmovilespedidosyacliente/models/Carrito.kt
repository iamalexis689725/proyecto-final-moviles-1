package com.example.appmovilespedidosyacliente.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Carrito(
    val product_id: Int,
    val nombre: String,
    var qty: Int,
    val price: Double
) : Parcelable
