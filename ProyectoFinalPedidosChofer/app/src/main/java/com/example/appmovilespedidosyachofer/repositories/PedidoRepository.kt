package com.example.appmovilespedidosyachofer.repositories

import JSONPlaceHolderService
import com.example.appmovilespedidosyachofer.models.misPedidosFree
import com.example.appmovilespedidosyacliente.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PedidoRepository {

    fun verPedidosSinAsignar(
        token: String,
        onSuccess: (misPedidosFree) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)

        service.verPedidosLibres("Bearer $token").enqueue(object : Callback<misPedidosFree> {
            override fun onResponse(call: Call<misPedidosFree>, response: Response<misPedidosFree>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError(Throwable("No se recibió la lista de pedidos"))
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<misPedidosFree>, t: Throwable) {
                onError(t)
                println("Error de red: ${t.message}")
            }
        })
    }
}