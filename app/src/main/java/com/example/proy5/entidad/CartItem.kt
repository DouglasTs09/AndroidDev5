package com.example.proy5.entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val id: String,
    val product: Producto,
    val quantity: Int
) : Serializable {
    override fun toString(): String {
        return "CartItem(id='$id', product=$product, quantity=$quantity)"
    }
}
