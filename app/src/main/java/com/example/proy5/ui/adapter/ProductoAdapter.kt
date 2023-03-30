package com.example.proy5.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.proy5.databinding.ItemProductoBinding
import com.example.proy5.entidad.Producto

class ProductoAdapter (val lista: List<Producto>): Adapter<ProductoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i("Cant Product", "lala" + lista.size)
        return lista.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = lista[position]
        holder.bind(producto)
    }


}