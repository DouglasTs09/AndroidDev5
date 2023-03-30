package com.example.proy5.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proy5.entidad.Producto
import com.example.proy5.repository.ListaRepository
import kotlinx.coroutines.launch
import com.example.proy5.entidad.Result
import com.example.proy5.entidad.SimpleMensaje

class HomeViewModel (private val repository: ListaRepository) : ViewModel() {

    private var _productos = MutableLiveData<List<Producto>>()
    val productos: LiveData<List<Producto>> get() = _productos

    private var _error = MutableLiveData<SimpleMensaje>()
    val error: LiveData<SimpleMensaje> get() = _error

    fun obtenerProductos() {
        Log.d("DBG 1","Antes de ViewModelScope")
        viewModelScope.launch {
            Log.d("DBG","HOMEBIEWMODEL ESTOY AQUI")
            when (val respuesta = repository.getProductos()) {
                is Result.Exito -> {
                    _productos.value = respuesta.data
                }
                is Result.Error -> {
                    _error.value = respuesta.mensaje
                }
            }
        }
    }
}