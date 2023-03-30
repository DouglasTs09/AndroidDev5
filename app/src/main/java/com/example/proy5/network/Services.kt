package com.example.proy5.network

import com.example.proy5.entidad.Data
import com.example.proy5.entidad.Producto
import retrofit2.http.GET

interface Services {

        @GET("productos.json")
        suspend fun getProductos(): List<Producto>
}