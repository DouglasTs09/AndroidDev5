package com.example.proy5.network

import com.example.proy5.entidad.Pedido
import com.example.proy5.entidad.Producto
import retrofit2.Response
import retrofit2.http.*

interface Services {

        @GET("productos.json")
        suspend fun getProductos(): List<Producto>

        @PUT("pedidos/{id}.json")
        suspend fun crearPedido(@Path("id") id:Int ,@Body pedido: Pedido): Response<Void>

        @GET("pedidos.json")
        suspend fun getPedidos(): List<Pedido>

}