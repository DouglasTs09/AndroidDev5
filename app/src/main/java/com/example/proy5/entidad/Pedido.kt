package com.example.proy5.entidad

import java.io.Serializable

class Pedido (
    val customer: Customer,
    val items: List<ItemPedido>,
    val subtotal: Double,
    val total: Double,
    val date: String
    ) : Serializable {
    override fun toString(): String {
        return "Pedido(customer=$customer, items=$items, subtotal=$subtotal, total=$total, date='$date')"
    }
}

class Customer (
    val name: String,
    val email: String,
    val phone: String,
    val dni: String
    ) : Serializable {
    override fun toString(): String {
        return "Customer(name='$name', email='$email', phone='$phone', dni='$dni')"
    }
}

class ItemPedido (
    val name : String,
    val quantity : Int,
    val price : Double
        ) : Serializable {
    override fun toString(): String {
        return "ItemPedido(name='$name', quantity=$quantity, price=$price)"
    }
}