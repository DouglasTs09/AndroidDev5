package com.example.proy5.ui.view

import android.os.Binder
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.NumberFormat
import java.time.LocalDateTime
import java.util.*

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
            if(lstItems.isNotEmpty()) {
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

                val formatter = NumberFormat.getCurrencyInstance(Locale("es","PE"))
                val subtotalString = formatter.format(subTotal)
                val igvString = formatter.format(igv)
                val totalString = formatter.format(total)

                binding.subtotalTextView.text = subtotalString
                binding.igvTextView.text = igvString
                binding.totalTextView.text = totalString

                binding.recyclerViewItems.layoutManager = LinearLayoutManager(requireContext())

                binding.btnComprar.setOnClickListener {
                    val itemsPedido = (binding.recyclerViewItems.adapter as CartItemAdapter).getItemsPedidos()
                    val subtotal = itemsPedido.fold(0.0) { acc, item -> acc + item.price*item.quantity}
                    val igv = subtotal * 0.18
                    val total = subtotal + igv
                    val date = LocalDateTime.now().toString()
                    val random = Random()
                    val codigo = random.nextInt(900000) + 100000
                    println(itemsPedido)
                    pedidoViewModel.crearPedido(
                        Customer(
                            "Katy Molina",
                            "dtafursadasdas@hotmail.com",
                            "989898989",
                            "88776655"
                        ),
                        itemsPedido,
                        subtotal,
                        total,
                        date,
                        codigo
                    )
                    MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_info)
                        .setTitle("N° Pedido: $codigo")
                        .setMessage("Por favor sirvase a pagar en su agente más cercano.")
                        .setPositiveButton("Finalizar") { _, _ ->
                            findNavController().popBackStack(R.id.homeFragment,false)
                            cartItemViewModel.deleteAll()
                        }
                        .show()
                }
            } else {
                binding.recyclerViewItems.visibility = View.GONE
                binding.subtotalTextView.visibility = View.GONE
                binding.igvTextView.visibility = View.GONE
                binding.totalTextView.visibility = View.GONE
                binding.igvLabel.visibility = View.GONE
                binding.subtotalLabel.visibility = View.GONE
                binding.totalLabel.visibility = View.GONE
                binding.btnComprar.text = "Ir a Home"
                binding.imageViewLstIsEmtpy.visibility = View.VISIBLE
                binding.textViewLstIsEmpty.visibility = View.VISIBLE

                binding.btnComprar.setOnClickListener {
                    findNavController().popBackStack(R.id.homeFragment,false)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}