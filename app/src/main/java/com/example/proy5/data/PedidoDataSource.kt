package com.example.proy5.data

import android.util.Log
import com.example.proy5.entidad.Data
import com.example.proy5.entidad.Pedido
import com.example.proy5.entidad.Result
import com.example.proy5.entidad.SimpleMensaje
import com.example.proy5.network.ConexionApi

class PedidoDataSource {

    suspend fun crearPedido(pedido: Pedido) {
        try {
            ConexionApi.retrofitService.crearPedido(pedido)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}