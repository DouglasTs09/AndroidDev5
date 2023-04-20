package com.example.proy5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemPedidoitemBinding
import com.example.proy5.entidad.ItemPedido

class ItemPedidoAdapter (val lista: List<ItemPedido> ): RecyclerView.Adapter<ItemPedidoViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPedidoViewHolder {
        val view = ItemPedidoitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPedidoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ItemPedidoViewHolder, position: Int) {
        val itempedido = lista[position]
        holder.bind(itempedido)
    }

}