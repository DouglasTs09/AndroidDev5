package com.example.proy5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proy5.entidad.Customer
import com.example.proy5.entidad.ItemPedido
import com.example.proy5.entidad.Pedido
import com.example.proy5.repository.PedidoRepository
import kotlinx.coroutines.launch

class PedidoViewModel (private val repository: PedidoRepository) : ViewModel() {

    private val _pedidoCreado = MutableLiveData<Pedido>()
    val pedidoCreado: LiveData<Pedido>
    get() = _pedidoCreado

    fun crearPedido(customer: Customer, items: List<ItemPedido>, subtotal: Double, total: Double, date: String) {
        viewModelScope.launch {
            val pedido = Pedido(customer, items, subtotal, total, date)
            repository.crearPedido(pedido)
            _pedidoCreado.value = pedido
        }
    }
}