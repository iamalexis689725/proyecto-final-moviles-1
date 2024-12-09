package com.example.appmovilespedidosyacliente.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovilespedidosyacliente.R
import com.example.appmovilespedidosyacliente.databinding.ActivityCarritoBinding
import com.example.appmovilespedidosyacliente.models.Carrito
import com.example.appmovilespedidosyacliente.models.DetallePedido
import com.example.appmovilespedidosyacliente.models.Pedido
import com.example.appmovilespedidosyacliente.repositories.PedidoRepository
import com.example.appmovilespedidosyacliente.ui.adapters.ListaDeCarritoAdapter
import com.example.appmovilespedidosyacliente.ui.viewmodels.PedidoViewModel

class CarritoActivity : AppCompatActivity() {

    private lateinit var carritoCompras: ArrayList<Carrito>
    private lateinit var binding: ActivityCarritoBinding
    private var restaurante_id: Int = -1
    private var token: String? = null

    private val viewModel: PedidoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreference = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
        token = sharedPreference.getString("ACCESS_TOKEN", null)

        carritoCompras = intent.getParcelableArrayListExtra("carrito_compras") ?: arrayListOf()
        restaurante_id = intent.getIntExtra("restaurant_id", -1)
        setupEventListener()
        setupRecyclerView()
    }

    private fun setupEventListener() {
        binding.btnAgregarUbicacion.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.btnCrearPedido.setOnClickListener {
            crearPedido()
        }
    }

    private fun crearPedido() {
        val detalles = carritoCompras.map {
            DetallePedido(
                product_id = it.product_id,
                qty = it.qty,
                price = it.price.toString()
            )
        }

        val pedido = Pedido(
            restaurant_id = restaurante_id,
            total = calcularTotal(),
            address = binding.txtDireccion.text.toString(),
            latitude = binding.lblLatitud.text.toString(),
            longitude = binding.lblLongitud.text.toString(),
            details = detalles
        )

        viewModel.crearPedido(
            token,
            pedido,
            onSuccess = {
                Toast.makeText(this, "Pedido creado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            },
            onError = {
                Toast.makeText(this, "Error al crear pedido", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun calcularTotal(): Double {
        var total = 0.0
        for (carrito in carritoCompras) {
            total += carrito.price * carrito.qty
        }
        return total
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.let {
                val latitude = it.getDoubleExtra("latitude", 0.0)
                val longitude = it.getDoubleExtra("longitude", 0.0)

                val lblLatitud = findViewById<TextView>(R.id.lblLatitud)
                val lblLongitud = findViewById<TextView>(R.id.lblLongitud)
                lblLatitud.text = latitude.toString()
                lblLongitud.text = longitude.toString()
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = ListaDeCarritoAdapter(carritoCompras) { calcularTotalProductos() }
        binding.rvCarrito.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@CarritoActivity)
        }
        calcularTotalProductos()
    }

    private fun calcularTotalProductos() {
        var total = 0.0
        for (carrito in carritoCompras) {
            total += carrito.price * carrito.qty
        }
        binding.lblTotal.text = "$total bs"
    }
}
