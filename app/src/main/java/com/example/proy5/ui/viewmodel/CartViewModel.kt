package com.example.proy5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.proy5.entidad.CartItem
import com.example.proy5.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel (private val repository: CartRepository) : ViewModel() {

    val todosLosItems : LiveData<List<CartItem>> = repository.listaCartItems.asLiveData()

    fun agregar(nuevo: CartItem) {
        viewModelScope.launch {
            repository.insertar(nuevo)
        }
    }

    fun actualizar(cartItem: CartItem) {
        viewModelScope.launch {
            repository.actualizar(cartItem)
        }
    }

    fun eliminar(cartItem: CartItem) {
        viewModelScope.launch {
            repository.eliminar(cartItem)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}