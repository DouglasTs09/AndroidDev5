package com.example.proy5.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proy5.repository.CartRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryCart(private val repository: CartRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        return super.create(modelClass)
    }

}