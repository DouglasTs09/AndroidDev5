package com.example.proy5.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.CartItemBinding
import com.example.proy5.entidad.CartItem
import com.example.proy5.entidad.Producto
import com.squareup.picasso.Picasso

class CartItemViewHolder (val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartItem: CartItem) {
        binding.productName.text = cartItem.name
        binding.productDescription.text = cartItem.description
        binding.productPrice.text = cartItem.price.toString()
        binding.productQuantity.text = cartItem.quantity.toString()
        val urlImage = cartItem.image_url
        Picasso.get().load(urlImage).into(binding.productImage)
    }

}