package com.example.proy5.repository

import android.util.Log
import com.example.proy5.data.ListaDataSource
import com.example.proy5.entidad.Producto
import com.example.proy5.entidad.Result

class ListaRepository(private val dataSource: ListaDataSource) {

    suspend fun getProductos(): Result<List<Producto>> {
        when (val respuesta = dataSource.listarProductos()) {
            is Result.Exito -> {
                Log.d("DEBUG","ListaRepository")
                val lista = respuesta.data.productos
                return Result.Exito(lista)
            }
            is Result.Error -> {
                return respuesta
            }
        }
    }

}