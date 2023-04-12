package com.example.proy5.app

import android.app.Application
import com.example.proy5.entidad.CartItemDataBase
import com.example.proy5.repository.CartRepository

class CartItemAplication: Application() {
    val database by lazy { CartItemDataBase.getDataBase(this) }
    val repository by lazy { CartRepository(database.cartItem()) }
}