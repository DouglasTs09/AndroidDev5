package com.example.proy5.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proy5.R
import com.example.proy5.data.ListaDataSource
import com.example.proy5.databinding.FragmentHomeBinding
import com.example.proy5.databinding.FragmentLoginBinding
import com.example.proy5.repository.ListaRepository
import com.example.proy5.ui.adapter.ProductoAdapter
import com.example.proy5.ui.viewmodel.HomeViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactory
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapterProducto: ProductoAdapter

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
        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapterProducto = ProductoAdapter(emptyList(), {
                producto, posicion ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailProductFragment(producto)
            findNavController().navigate(action)
        } )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.VISIBLE

        Log.i("home","estoy aquí")
        viewModel.obtenerProductos()

        viewModel.productos.observe(viewLifecycleOwner) { data ->

            binding.apply {
                recyclerViewProducts.setHasFixedSize(true)
                val carouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
                carouselLayoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
                carouselLayoutManager.setCircleLayout(true)
                recyclerViewProducts.layoutManager = carouselLayoutManager
                recyclerViewProducts.addOnScrollListener(CenterScrollListener())
                adapterProducto = ProductoAdapter(data, {
                        producto, posicion ->
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailProductFragment(producto)
                    findNavController().navigate(action)
                } )
                recyclerViewProducts.adapter = adapterProducto
            }

        }

        binding.imageButtonCart.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
            findNavController().navigate(action)
        }

        binding.editTextSearch.addTextChangedListener {
                val query = binding.editTextSearch.text.toString()
                adapterProducto.filter(query)
        }

        binding.buttonMonitores.setOnClickListener {
            adapterProducto.filterByCategory("monitores")
        }

        binding.buttonPcs.setOnClickListener {
            adapterProducto.filterByCategory("pcs")
        }

        binding.buttonPerifericos.setOnClickListener {
            adapterProducto.filterByCategory("perifericos")
        }

        binding.buttonProcesadores.setOnClickListener {
            adapterProducto.filterByCategory("procesadores")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.editTextSearch.setText("")
    }

    override fun onPause() {
        super.onPause()
        binding.editTextSearch.setText("")
    }

    override fun onStop() {
        super.onStop()
        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.GONE
    }
}