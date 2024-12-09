package com.example.appmovilespedidosyachofer.repositories

import JSONPlaceHolderService
import com.example.appmovilespedidosyacliente.models.Token
import com.example.appmovilespedidosyacliente.models.Usuario
import com.example.appmovilespedidosyacliente.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UsuarioRepository {

    fun postCrearUsuario(
        usuario: Usuario,
        onSuccess: (Usuario) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.crearUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val createdUsuario = response.body()
                    onSuccess(createdUsuario!!)
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }

    fun postLogin(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        val credentials = mapOf("email" to email, "password" to password)

        service.inicioSesionUsuario(credentials).enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    onSuccess(loginResponse!!.access_token)
                } else {
                    val error = response.errorBody()?.string() ?: "Error desconocido"
                    onError(Throwable(error))
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                onError(t)
            }
        })
    }
}