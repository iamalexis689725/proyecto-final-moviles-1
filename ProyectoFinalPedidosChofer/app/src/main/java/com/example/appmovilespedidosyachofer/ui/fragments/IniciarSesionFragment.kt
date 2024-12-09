package com.example.appmovilespedidosyachofer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appmovilespedidosyachofer.R
import com.example.appmovilespedidosyachofer.databinding.FragmentIniciarSesionBinding
import com.example.appmovilespedidosyachofer.repositories.UsuarioRepository

class IniciarSesionFragment : Fragment() {

    private var _binding: FragmentIniciarSesionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIniciarSesionBinding.inflate(inflater, container, false)

        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.buttonLogin.setOnClickListener {
            iniciarSesion()
        }
        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(R.id.action_iniciarSesionFragment_to_registroFragment)
        }
    }

    private fun iniciarSesion() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        UsuarioRepository.postLogin(
            email,
            password,
            onSuccess = { token ->
                Toast.makeText(requireContext(), "BIENVENIDO", Toast.LENGTH_LONG).show()
                val sharedPreferences = requireActivity().getSharedPreferences("APP_PREFS", AppCompatActivity.MODE_PRIVATE)
                sharedPreferences.edit().putString("ACCESS_TOKEN", token).apply()
                findNavController().navigate(R.id.action_iniciarSesionFragment_to_listaPedidosFreeFragment)
            },
            onError = { error ->
                Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = IniciarSesionFragment()
    }
}
