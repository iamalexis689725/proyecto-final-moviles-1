package com.example.appmovilespedidosyacliente.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovilespedidosyacliente.R
import com.example.appmovilespedidosyacliente.databinding.ItemListaCarritoBinding
import com.example.appmovilespedidosyacliente.models.Carrito
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaDeCarritoAdapter(
    private val carritoCompras: ArrayList<Carrito>,
    private val cambioCantidad: () -> Unit
) : RecyclerView.Adapter<ListaDeCarritoAdapter.CarritoProductoItemViewHolder>()  {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarritoProductoItemViewHolder {
        val binding = ItemListaCarritoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarritoProductoItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return carritoCompras.size
    }

    override fun onBindViewHolder(holder: CarritoProductoItemViewHolder, position: Int) {
        holder.bind(carritoCompras[position]) { carrito, change ->
            carrito.qty += change
            notifyItemChanged(position)
            cambioCantidad()
        }
    }

    class CarritoProductoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lblNombreRestaurante: TextView = itemView.findViewById(R.id.lblNombreRestaurante)
        private val lblNombreRestauranteProducto: TextView = itemView.findViewById(R.id.lblNombreRestauranteProducto)
        private val lblCantidadCarritoProducto: TextView = itemView.findViewById(R.id.lblCantidadCarritoProducto)
        private val lblPrecioProductoCarrito: TextView = itemView.findViewById(R.id.lblPrecioProductoCarrito)
        private val btnAumentar: Button = itemView.findViewById(R.id.btnAumentar)
        private val btnDisminuir: Button = itemView.findViewById(R.id.btnDisminuir)

        private val fabBorrarProducto : FloatingActionButton = itemView.findViewById(R.id.fabBorrarProducto)

        fun bind(carrito: Carrito, cambioCantidad: (Carrito, Int) -> Unit) {
            lblNombreRestaurante.text = carrito.nombre
            lblNombreRestauranteProducto.text = carrito.nombre
            lblCantidadCarritoProducto.text = carrito.qty.toString()
            updatePriceDisplay(carrito)

            btnAumentar.setOnClickListener {
                cambioCantidad(carrito, 1)
            }

            btnDisminuir.setOnClickListener {
                if (carrito.qty > 1) {
                    cambioCantidad(carrito, -1)
                }
            }
        }

        private fun updatePriceDisplay(carrito: Carrito) {
            val totalPrice = carrito.price * carrito.qty
            lblPrecioProductoCarrito.text = "${totalPrice} bs"
        }
    }

}