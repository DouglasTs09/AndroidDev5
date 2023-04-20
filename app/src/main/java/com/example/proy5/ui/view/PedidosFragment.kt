package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.proy5.R
import com.example.proy5.data.PedidoDataSource
import com.example.proy5.databinding.FragmentPedidosBinding
import com.example.proy5.repository.PedidoRepository
import com.example.proy5.ui.adapter.PedidoAdapter
import com.example.proy5.ui.viewmodel.PedidoViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactoryPedido

class PedidosFragment : Fragment() {

    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PedidosFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val viewModel: PedidoViewModel = ViewModelFactoryPedido(PedidoRepository(PedidoDataSource())).create()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        _binding = FragmentPedidosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.VISIBLE

        viewModel.obtenerPedidos()

        viewModel.pedidos.observe (viewLifecycleOwner) { data ->
            println(data)
            val adapter = PedidoAdapter(data, {
                    pedido, posicion ->
                val action =  PedidosFragmentDirections.actionPedidosFragmentToDetailPedidoFragment(pedido)
                findNavController().navigate(action)
            } )
            binding.recyclerViewPedidos.adapter = adapter

        }
    }

}