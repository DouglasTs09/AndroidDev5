package com.example.proy5.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemProductoBinding
import com.example.proy5.entidad.Producto
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class ProductoViewHolder (private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(producto: Producto) {
        val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))
        val priceString = formatter.format(producto.price)
        binding.textViewProductoTitulo.text = producto.name
        binding.textViewProductoPrecio.text = priceString

        val urlImage = producto.image_url
        Log.i("Imagen",urlImage)
        Picasso.get().load(urlImage).into(binding.imageViewProducto)
    }

}