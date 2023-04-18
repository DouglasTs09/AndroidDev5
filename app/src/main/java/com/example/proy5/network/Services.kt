package com.example.proy5.network

import com.example.proy5.entidad.Pedido
import com.example.proy5.entidad.Producto
import com.example.proy5.entidad.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
interface Services {

        @GET("productos.json")
        suspend fun getProductos(): List<Producto>

        @POST("pedidos.json")
        suspend fun crearPedido(@Body pedido: Pedido): Response<Void>

}