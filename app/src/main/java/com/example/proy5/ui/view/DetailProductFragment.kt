package com.example.proy5.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proy5.R
import com.example.proy5.app.CartItemAplication
import com.example.proy5.data.ListaDataSource
import com.example.proy5.databinding.FragmentDetailProductBinding
import com.example.proy5.entidad.CartItem
import com.example.proy5.repository.CartRepository
import com.example.proy5.repository.ListaRepository
import com.example.proy5.ui.viewmodel.CartViewModel
import com.example.proy5.ui.viewmodel.HomeViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactory
import com.example.proy5.ui.viewmodel.ViewModelFactoryCart
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class DetailProductFragment : Fragment() {

    private var _binding : FragmentDetailProductBinding? = null
    val binding get() = _binding!!

    private val productoViewModel : HomeViewModel = ViewModelFactory(ListaRepository(ListaDataSource())).create()

    private val cartItemViewModel: CartViewModel by viewModels {
        ViewModelFactoryCart((requireActivity().application as CartItemAplication).repository)
    }

    private  val args: DetailProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productSelect = args.productDetail
        Log.i("MSG","Se productoDetail:" + productSelect)


        binding.textViewProductoTitulo.text = productSelect?.name ?: ""
            binding.textViewProductoPrecio.text = productSelect?.price.toString() ?: "0.00"
            val urlImage = productSelect?.image_url
            Picasso.get().load(urlImage).into(binding.imageViewProducto)

            binding.numberPicker.minValue = 1
            binding.numberPicker.maxValue = 10
            binding.numberPicker.value = 1
            binding.numberPicker.wrapSelectorWheel = true

        binding.buttonAgregarAlCarrito.setOnClickListener {

            val cartItem = productSelect?.let {
                CartItem(
                    name= it.name,
                    description = productSelect.description,
                    price = productSelect.price,
                    quantity = binding.numberPicker.value,
                    image_url = productSelect.image_url
                )
            }

            MaterialAlertDialogBuilder(requireContext())
                .setIcon(R.drawable.ic_add)
                .setTitle("Agregar")
                .setMessage("¿Desea agregar este producto?")
                .setPositiveButton("Agregar") { _, _ ->
                    if (cartItem != null) {
                        cartItemViewModel.agregar(cartItem)
                    }
                    MaterialAlertDialogBuilder(requireContext())
                        .setIcon(R.drawable.ic_info)
                        .setPositiveButton("Seguir Comprando") { _, _ ->
                            if (cartItem != null) {
                                cartItemViewModel.agregar(cartItem)
                            }
                            findNavController().popBackStack()
                        }
                        .setNegativeButton("Ir al carrito de compras") { _,_ ->
                            val action = DetailProductFragmentDirections.actionDetailProductFragmentToCartFragment()
                            findNavController().navigate(action)
                        }
                        .show()
                }
                .setNegativeButton("Cancelar", null)
                .setNeutralButton("Cerrar", null)
                .show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}