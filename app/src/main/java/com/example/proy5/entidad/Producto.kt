package com.example.proy5.entidad

import java.io.Serializable
import java.util.*

class Producto (
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val description: String,
    val image_url: String,
    val name: String,
    val price: Double
    ) : Serializable {
    override fun toString(): String {
        return "Producto(id='$id', category='$category', description='$description', image_url='$image_url', name='$name', price=$price)"
    }
}