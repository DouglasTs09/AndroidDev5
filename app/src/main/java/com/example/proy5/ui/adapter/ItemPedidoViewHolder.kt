package com.example.proy5.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemPedidoitemBinding
import com.example.proy5.entidad.ItemPedido
import java.text.NumberFormat
import java.util.*

class ItemPedidoViewHolder (private val binding: ItemPedidoitemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itempedido: ItemPedido) {
        val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))
        val priceString = formatter.format(itempedido.price)
        val priceTotal = itempedido.price * itempedido.quantity
        val priceTotalString = formatter.format(priceTotal)
        binding.tvName.text = itempedido.name
        binding.tvPrice.text = priceString
        binding.tvTotal.text = priceTotalString
        binding.tvCantidad.text = itempedido.quantity.toString()
    }

}