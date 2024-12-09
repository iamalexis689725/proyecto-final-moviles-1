package com.example.appmovilespedidosyacliente.repositories

import JSONPlaceHolderService

import com.example.appmovilespedidosyacliente.models.Pedido
import com.example.appmovilespedidosyacliente.models.PedidosDelUsuarios
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PedidoRepository {

    fun crearPedido(
        token: String,
        pedido: Pedido,
        onSuccess: (Pedido) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)

        service.crearPedido("Bearer $token", pedido).enqueue(object : Callback<Pedido> {
            override fun onResponse(call: Call<Pedido>, response: Response<Pedido>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                        println("Pedido creado con éxito: $it")
                    } ?: onError(Throwable("No se recibió el pedido"))
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<Pedido>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun verPedidosDelUsuariosUsuario(
        token: String,
        onSuccess: (PedidosDelUsuarios) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)


        service.verPedidosUsuario("Bearer $token").enqueue(object : Callback<PedidosDelUsuarios> {
            override fun onResponse(call: Call<PedidosDelUsuarios>, response: Response<PedidosDelUsuarios>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                        println("Pedidos obtenidos con éxito: $it")
                    } ?: onError(Throwable("No se recibió la lista de pedidos"))
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<PedidosDelUsuarios>, t: Throwable) {
                onError(t)
                println("Error de red: ${t.message}")
            }
        })
    }
}
