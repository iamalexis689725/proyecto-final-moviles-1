package com.example.appmovilespedidosyachofer.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.findNavController
import com.example.appmovilespedidosyachofer.R
import com.example.appmovilespedidosyachofer.databinding.FragmentRegistroBinding
import com.example.appmovilespedidosyacliente.models.Usuario
import com.example.appmovilespedidosyachofer.ui.viewmodels.UsuarioViewModel

class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        setupInsets()
        setupListeners()
        return binding.root
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListeners() {
        binding.buttonRegister.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = Usuario(
            name = name,
            email = email,
            password = password,
            role = 2
        )

        viewModel.crearUsuario(usuario,
            onSuccess = {
                Toast.makeText(requireContext(), "Usuario chofer creado correctamente", Toast.LENGTH_SHORT).show()
                navegarAIniciarSesion()
            },
            onError = {
                Toast.makeText(requireContext(), "Error al crear usuario chofer", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun navegarAIniciarSesion() {
        findNavController().navigate(R.id.action_registroFragment_to_iniciarSesionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
