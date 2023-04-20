package com.example.proy5.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.CartItemBinding
import com.example.proy5.entidad.CartItem
import com.example.proy5.entidad.Producto
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class CartItemViewHolder (val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartItem: CartItem) {
        val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))
        val priceString = formatter.format(cartItem.price)
        val total = cartItem.price * cartItem.quantity
        val totalString = formatter.format(total)
        binding.productName.text = cartItem.name
        binding.productDescription.text = cartItem.description
        binding.productPrice.text = priceString
        binding.productQuantity.text = cartItem.quantity.toString() + " u."
        binding.productTotal.text = totalString
        val urlImage = cartItem.image_url
        Picasso.get().load(urlImage).into(binding.productImage)
    }

}