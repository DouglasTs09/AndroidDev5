package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.proy5.R
import com.example.proy5.data.ListaDataSource
import com.example.proy5.databinding.FragmentDetailProductBinding
import com.example.proy5.repository.ListaRepository
import com.example.proy5.ui.viewmodel.HomeViewModel
import com.example.proy5.ui.viewmodel.ViewModelFactory

class DetailProductFragment : Fragment() {

    private var _binding : FragmentDetailProductBinding? = null
    val binding get() = _binding!!

    private val productoViewModel : HomeViewModel = ViewModelFactory(ListaRepository(ListaDataSource())).create()

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

            binding.textViewProductoTitulo.text = productSelect?.name ?: ""
            binding.textViewProductoPrecio.text = productSelect?.price.toString() ?: "0.00"

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}