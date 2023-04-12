package com.example.proy5.repository

import com.example.proy5.entidad.CartItem
import com.example.proy5.entidad.CartItemDao
import kotlinx.coroutines.flow.Flow

class CartRepository (private val cartItemDao: CartItemDao) {

    val listaCartItems : Flow<List<CartItem>> = cartItemDao.obtenerItemsCart()

    suspend fun insertar(cartItem: CartItem) {
        cartItemDao.insertarCartItem(cartItem)
    }

    suspend fun actualizar(cartItem: CartItem) {
        cartItemDao.actualizarCartItem(cartItem)
    }

    suspend fun eliminar(cartItem: CartItem) {
        cartItemDao.eliminarCartItem(cartItem)
    }
}