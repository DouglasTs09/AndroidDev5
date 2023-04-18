package com.example.proy5.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proy5.data.ListaDataSource
import com.example.proy5.repository.ListaRepository
import com.example.proy5.repository.PedidoRepository

/*class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {

                val repoLista = ListaRepository(ListaDataSource())

                return HomeViewModel(repoLista) as T
            }
        }
        return super.create(modelClass)
    }
}*/
// Definition of a Factory interface with a function to create objects of a type
interface Factory {
    fun create(): HomeViewModel
}

// HomeViewModel, you need an instance of UserRepository that you pass as a parameter.
class ViewModelFactory(private val listaRepository: ListaRepository) : Factory {
    override fun create(): HomeViewModel {
        return HomeViewModel(listaRepository)
    }
}

interface FactoryPedido {
    fun create(): PedidoViewModel
}

class ViewModelFactoryPedido(private val pedidoRepository: PedidoRepository) : FactoryPedido {
    override fun create(): PedidoViewModel {
        return PedidoViewModel(pedidoRepository)
    }
}