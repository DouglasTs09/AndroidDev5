package com.example.proy5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proy5.databinding.ItemPedidoBinding
import com.example.proy5.entidad.Pedido
import java.util.*

class PedidoAdapter (val lista: List<Pedido>, val click: (Pedido, Int) -> Unit): RecyclerView.Adapter<PedidoViewHolder>()  {

    init {
        Collections.reverse(lista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = ItemPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = lista[position]
        holder.bind(pedido)
        holder.itemView.setOnClickListener {
            click(pedido, holder.layoutPosition)
        }
    }
}