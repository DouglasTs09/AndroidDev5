package com.example.proy5.entidad

import java.io.Serializable

class Producto (
    val category: String,
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Double
    ) : Serializable {
    override fun toString(): String {
        return "Producto(category='$category', description='$description', id=$id, image_url='$image_url', name='$name', price=$price)"
    }
}