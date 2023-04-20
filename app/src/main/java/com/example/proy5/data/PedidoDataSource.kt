package com.example.proy5.data

import android.util.Log
import com.example.proy5.entidad.*
import com.example.proy5.network.ConexionApi

class PedidoDataSource {

    suspend fun crearPedido(pedido: Pedido) {
        try {
            val lenght = ConexionApi.retrofitService.getPedidos().count()
            ConexionApi.retrofitService.crearPedido(lenght,pedido)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    suspend fun listarPedidos(): Result<DataPedidos> {
        try {
            val resultado = ConexionApi.retrofitService.getPedidos()
            Log.d("MSG",resultado.toString())
            return Result.Exito(DataPedidos(resultado))
        } catch (ex: Exception) {
            ex.printStackTrace()
            val error = SimpleMensaje("0001",ex.toString())
            return Result.Error(error)
        }
    }

}