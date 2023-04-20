package com.example.proy5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proy5.entidad.*
import com.example.proy5.repository.PedidoRepository
import kotlinx.coroutines.launch

class PedidoViewModel (private val repository: PedidoRepository) : ViewModel() {

    private val _pedidoCreado = MutableLiveData<Pedido>()
    val pedidoCreado: LiveData<Pedido>
    get() = _pedidoCreado

    private var _pedidos = MutableLiveData<List<Pedido>>()
    val pedidos: LiveData<List<Pedido>> get() = _pedidos

    private var _error = MutableLiveData<SimpleMensaje>()
    val error: LiveData<SimpleMensaje> get() = _error

    fun crearPedido(customer: Customer, items: List<ItemPedido>, subtotal: Double, total: Double, date: String, codigo: Int) {
        viewModelScope.launch {
            val pedido = Pedido(customer, items, subtotal, total, date, codigo)
            repository.crearPedido(pedido)
            _pedidoCreado.value = pedido
        }
    }

    fun obtenerPedidos() {
        viewModelScope.launch {
            when (val respuesta = repository.getPedidos()) {
                is Result.Exito -> {
                    _pedidos.value = respuesta.data
                }
                is Result.Error -> {
                    _error.value = respuesta.mensaje
                }
            }
        }
    }
}