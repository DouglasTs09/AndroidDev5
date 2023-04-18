package com.example.proy5.ui.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proy5.R
import com.example.proy5.app.CartItemAplication
import com.example.proy5.data.ListaDataSource
import com.example.proy5.data.PedidoDataSource
import com.example.proy5.databinding.FragmentCartBinding
import com.example.proy5.entidad.Customer
import com.example.proy5.repository.ListaRepository
import com.example.proy5.repository.PedidoRepository
import com.example.proy5.ui.adapter.CartItemAdapter
import com.example.proy5.ui.viewmodel.*
import java.time.LocalDateTime

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartItemViewModel: CartViewModel by viewModels {
        ViewModelFactoryCart((requireActivity().application as CartItemAplication).repository)
    }

    private val pedidoViewModel: PedidoViewModel = ViewModelFactoryPedido(PedidoRepository(PedidoDataSource())).create()

    companion object {
        fun newInstance() = CartFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartItemViewModel.todosLosItems.observe(viewLifecycleOwner) { lstItems ->
            binding.recyclerViewItems.adapter = CartItemAdapter(
                lstItems,
                { item, posicion ->
                    val action = CartFragmentDirections.actionCartFragmentToEditCartItemFragment(item)
                    findNavController().navigate(action)
                }
            )
            val subTotal = lstItems.fold(0.0) { acc, item -> acc + item.price*item.quantity}
            val igv = subTotal * 0.18
            val total = subTotal + igv

            binding.subtotalTextView.text = subTotal.toString()
            binding.igvTextView.text = igv.toString()
            binding.totalTextView.text = total.toString()
        }

        binding.recyclerViewItems.layoutManager = LinearLayoutManager(requireContext())

        binding.btnComprar.setOnClickListener {
            val itemsPedido = (binding.recyclerViewItems.adapter as CartItemAdapter).getItemsPedidos()
            val subtotal = itemsPedido.fold(0.0) { acc, item -> acc + item.price*item.quantity}
            val igv = subtotal * 0.18
            val total = subtotal + igv
            val date = LocalDateTime.now().toString()
            println(itemsPedido)
            pedidoViewModel.crearPedido(
                Customer(
                    "Douglas Andreus",
                    "dtafursadasdas@hotmail.com",
                    "989898989",
                    "88776655"
                ),
                itemsPedido,
                subtotal,
                total,
                date
            )
        }
        
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}