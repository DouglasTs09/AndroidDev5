package com.example.proy5.entidad

class Data (val productos: List<Producto>) {
    override fun toString(): String {
        return "Data(productos=$productos)"
    }
}

class DataPedidos (val pedidos: List<Pedido>) {
    override fun toString(): String {
        return "DataPedidos(pedidos=$pedidos)"
    }
}