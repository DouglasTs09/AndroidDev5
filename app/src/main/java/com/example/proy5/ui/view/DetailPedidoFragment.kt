package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proy5.databinding.FragmentDetailPedidoBinding
import com.example.proy5.ui.adapter.ItemPedidoAdapter
import java.text.NumberFormat
import java.util.*

class DetailPedidoFragment : Fragment() {

    private var _binding : FragmentDetailPedidoBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailPedidoFragment()
    }

    val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val args: DetailPedidoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailPedidoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pedidoDetail = args.pedido

        val fechaHora = pedidoDetail?.date
        val fecha = fechaHora?.substring(8,10)+"/"+fechaHora?.substring(5,7)+"/"+fechaHora?.substring(0,4)
        val hora = fechaHora?.substring(11,19)

        val items = pedidoDetail?.items
        val igv = pedidoDetail?.total!! - pedidoDetail?.subtotal!!
        val subtotalString = formatter.format(pedidoDetail?.subtotal)
        val totalString = formatter.format(pedidoDetail?.total)
        val igvString = formatter.format((igv))

        binding.tvNPedido.text = pedidoDetail.codigo.toString()
        binding.tvFecha.text = fecha
        binding.tvHora.text = hora
        binding.tvSubTotal.text = subtotalString
        binding.tvIGV.text = igvString
        binding.tvTotal.text  = totalString

        binding.rvItems.adapter = items?.let { ItemPedidoAdapter(it) }
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext())

    }
}