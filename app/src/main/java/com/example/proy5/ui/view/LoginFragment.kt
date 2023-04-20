package com.example.proy5.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proy5.MainActivity
import com.example.proy5.R
import com.example.proy5.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNav = activity?.findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.GONE

        binding.buttonLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            val username = binding.editTextUsername.text
            val password = binding.editTextPassword.text
            if (username.isEmpty() || password.isEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_info)
                    .setTitle("Error")
                    .setMessage("Debe ingresar usuario y/o contraseña.")
                    .setNeutralButton("Cerrar", null)
                    .show()
            } else {
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}