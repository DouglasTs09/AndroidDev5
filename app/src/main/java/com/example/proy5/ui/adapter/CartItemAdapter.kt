package com.example.proy5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.CartItemBinding
import com.example.proy5.entidad.CartItem
import com.example.proy5.entidad.ItemPedido

class CartItemAdapter (val lista: List<CartItem>, val click: (CartItem, Int) -> Unit) : RecyclerView.Adapter<CartItemViewHolder>() {

    fun getItemsPedidos(): List<ItemPedido> {
        return lista.map { item ->
            ItemPedido(item.name, item.quantity, item.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem= lista[position]
        holder.bind(cartItem)
        holder.itemView.setOnClickListener {
            click(cartItem, holder.layoutPosition)
        }
    }

}