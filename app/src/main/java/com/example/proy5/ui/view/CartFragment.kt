package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proy5.R
import com.example.proy5.app.CartItemAplication
import com.example.proy5.databinding.FragmentCartBinding
import com.example.proy5.ui.adapter.CartItemAdapter
import com.example.proy5.ui.viewmodel.CartViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactory
import com.example.proy5.ui.viewmodel.ViewModelFactoryCart

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartItemViewModel: CartViewModel by viewModels {
        ViewModelFactoryCart((requireActivity().application as CartItemAplication).repository)
    }

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
        }

        binding.recyclerViewItems.layoutManager = LinearLayoutManager(requireContext())
        
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}