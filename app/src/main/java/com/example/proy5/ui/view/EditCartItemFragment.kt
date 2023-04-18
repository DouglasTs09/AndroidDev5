package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proy5.R
import com.example.proy5.app.CartItemAplication
import com.example.proy5.databinding.FragmentEditCartItemBinding
import com.example.proy5.ui.viewmodel.CartViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactoryCart
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EditCartItemFragment : Fragment() {

    private var _binding : FragmentEditCartItemBinding? = null
    val binding get() = _binding!!

    private val cartItemViewModel: CartViewModel by viewModels {
        ViewModelFactoryCart((requireActivity().application as CartItemAplication).repository)
    }

    private  val args: EditCartItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditCartItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemSelect = args.cartItem

            binding.numberPicker.minValue = 1
            binding.numberPicker.maxValue = 10
            binding.numberPicker.value = itemSelect?.quantity ?: 1
            binding.numberPicker.wrapSelectorWheel = true

            binding.btnActualizar.setOnClickListener {

                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_info)
                    .setTitle("Confirmación")
                    .setMessage("¿Desea actualizar este producto?")
                    .setPositiveButton("Aceptar") { _, _ ->
                        val itemActualizado = itemSelect?.let { it1 ->
                            itemSelect.copy(
                                id = it1.id,
                                name = itemSelect.name,
                                description = itemSelect.description,
                                price = itemSelect.price,
                                quantity = binding.numberPicker.value,
                                image_url = itemSelect.image_url)
                        }
                        itemActualizado?.let { it1 -> cartItemViewModel.actualizar(it1) }
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("Cancelar", null)
                    .setNeutralButton("Cerrar",null)
                    .show()
            }

            binding.btnEliminar.setOnClickListener {

                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_warning)
                    .setTitle("Advertencia")
                    .setMessage("¿Desea eliminar este producto?")
                    .setPositiveButton("Eliminar") { _, _ ->
                        cartItemViewModel.eliminar(itemSelect!!)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("Cancelar", null)
                    .setNeutralButton("Cerrar", null)
                    .show()
            }

        }
    }
