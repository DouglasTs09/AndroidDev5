package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.proy5.R
import com.example.proy5.databinding.FragmentHomeBinding
import com.example.proy5.databinding.FragmentPedidosBinding
import com.example.proy5.databinding.FragmentUserBinding
import com.example.proy5.ui.adapter.ProductoAdapter

class UserFragment : Fragment() {

    private lateinit var _binding: FragmentUserBinding
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = UserFragment()
    }

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
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.VISIBLE

        binding.btnLogout.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}