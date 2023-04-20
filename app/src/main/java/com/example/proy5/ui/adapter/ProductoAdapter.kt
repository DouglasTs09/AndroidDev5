package com.example.proy5.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.proy5.databinding.ItemProductoBinding
import com.example.proy5.entidad.Producto

class ProductoAdapter (val lista: List<Producto>, val click: (Producto, Int) -> Unit): Adapter<ProductoViewHolder>() {

    private var listaFiltrada: List<Producto> = lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaFiltrada.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaFiltrada[position]
        holder.bind(producto)
        holder.itemView.setOnClickListener {
            click(producto, holder.layoutPosition)
        }
    }

    fun filter(text: String) {
        listaFiltrada = if (text.isEmpty()) {
            lista
        } else {
            lista.filter {
                it.name.contains(text, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    fun filterByCategory(category: String) {
        listaFiltrada = if (category.isBlank()) {
            lista
        } else {
            lista.filter { it.category == category }
        }
        notifyDataSetChanged()
    }
}