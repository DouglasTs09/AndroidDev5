package com.example.proy5.repository

import android.util.Log
import com.example.proy5.data.PedidoDataSource
import com.example.proy5.entidad.Pedido
import com.example.proy5.entidad.Producto
import com.example.proy5.entidad.Result

class PedidoRepository(private val dataSource: PedidoDataSource) {

    suspend fun crearPedido(pedido: Pedido) {
        dataSource.crearPedido(pedido)
    }

    suspend fun getPedidos(): Result<List<Pedido>> {
        when (val respuesta = dataSource.listarPedidos()) {
            is Result.Exito -> {
                Log.d("DEBUG","ListaRepository")
                val lista = respuesta.data.pedidos
                return Result.Exito(lista)
            }
            is Result.Error -> {
                return respuesta
            }
        }
    }

}