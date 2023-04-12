package com.example.proy5.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.CartItemBinding
import com.example.proy5.entidad.CartItem
import com.example.proy5.entidad.Producto

class CartItemViewHolder (val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartItem: CartItem) {
        binding.productName.text = cartItem.product.name
        binding.productDescription.text = cartItem.product.description
        binding.productPrice.text = cartItem.product.price.toString()
        binding.productQuantity.text = cartItem.quantity.toString()
    }

}