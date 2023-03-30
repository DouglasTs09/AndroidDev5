package com.example.proy5.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemProductoBinding
import com.example.proy5.entidad.Producto
import com.squareup.picasso.Picasso

class ProductoViewHolder (private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: Producto) {
        binding.textViewProductoTitulo.text = producto.name
        binding.textViewProductoPrecio.text = producto.price.toString()

        //val urlImage = producto.image_url
        //Picasso.get().load(urlImage).into(binding.imageViewProducto)
    }

}