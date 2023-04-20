package com.example.proy5.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemPedidoBinding
import com.example.proy5.entidad.Pedido
import com.example.proy5.entidad.Producto
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class PedidoViewHolder (private val binding: ItemPedidoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pedido: Pedido) {
        val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))
        val priceString = formatter.format(pedido.total)

        val fechaHora = pedido.date
        val fecha = fechaHora.substring(8,10)+"/"+fechaHora.substring(5,7)+"/"+fechaHora.substring(0,4)
        val codigo = pedido.codigo


        binding.textViewDate.text = "$fecha"
        binding.textViewCodigo.text = "N° Pedido: $codigo"

        val itemsNames = pedido.items.map { it.name }
        val itemsNamesList = itemsNames.joinToString(", ")

        binding.textViewItemName.text = itemsNamesList
        binding.textViewItemPrice.text = priceString

    }
}