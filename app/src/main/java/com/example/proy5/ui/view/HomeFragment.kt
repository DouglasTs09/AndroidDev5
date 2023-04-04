package com.example.proy5.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proy5.R
import com.example.proy5.data.ListaDataSource
import com.example.proy5.databinding.FragmentHomeBinding
import com.example.proy5.databinding.FragmentLoginBinding
import com.example.proy5.repository.ListaRepository
import com.example.proy5.ui.adapter.ProductoAdapter
import com.example.proy5.ui.viewmodel.HomeViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel = ViewModelFactory(ListaRepository(ListaDataSource())).create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.VISIBLE

        Log.i("home","estoy aquí")
        viewModel.obtenerProductos()

        viewModel.productos.observe(viewLifecycleOwner) { data ->
            println("OBSERVE: "+data)
            val adapter = ProductoAdapter(data, {
                    producto, posicion ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailProductFragment(producto)
                findNavController().navigate(action)
            } )
            binding.recyclerViewProducts.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.GONE
    }
}