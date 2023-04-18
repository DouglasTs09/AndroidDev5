package com.example.proy5.repository

import com.example.proy5.data.PedidoDataSource
import com.example.proy5.entidad.Pedido

class PedidoRepository(private val dataSource: PedidoDataSource) {

    suspend fun crearPedido(pedido: Pedido) {
        dataSource.crearPedido(pedido)
    }

}