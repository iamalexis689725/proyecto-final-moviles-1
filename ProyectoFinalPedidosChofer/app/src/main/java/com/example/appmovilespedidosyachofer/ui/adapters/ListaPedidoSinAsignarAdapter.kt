package com.example.appmovilespedidosyachofer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovilespedidosyachofer.R
import com.example.appmovilespedidosyachofer.databinding.ItemListaPedidosFreeBinding
import com.example.appmovilespedidosyachofer.models.PedidoFree
import com.example.appmovilespedidosyachofer.models.misPedidosFree
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaPedidoSinAsignarAdapter(private val listener: PedidoSinAsinarItemListener):
    RecyclerView.Adapter<ListaPedidoSinAsignarAdapter.PedidoSinAsinarItemViewHolder>() {

    private var listaDePedidosSinAsignar: misPedidosFree = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PedidoSinAsinarItemViewHolder {
        val binding = ItemListaPedidosFreeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoSinAsinarItemViewHolder(binding.root, listener)
    }

    override fun getItemCount(): Int {
        return listaDePedidosSinAsignar.size
    }

    override fun onBindViewHolder(holder: PedidoSinAsinarItemViewHolder, position: Int) {
        holder.bind(listaDePedidosSinAsignar[position])
    }

    fun updateData(it: misPedidosFree) {
        listaDePedidosSinAsignar = it
        notifyDataSetChanged()
    }


    class PedidoSinAsinarItemViewHolder(itemView: View, private val listener: PedidoSinAsinarItemListener) : RecyclerView.ViewHolder(itemView) {

        private var lblNumeroPedido: TextView = itemView.findViewById(R.id.lblNumeroPedido)
        private var lblRestauranteNombrePedido: TextView = itemView.findViewById(R.id.lblRestauranteNombrePedido)
        private var lblPrecioProductoPedido: TextView = itemView.findViewById(R.id.lblPrecioProductoPedido)
        private var lblEstadoPedido: TextView = itemView.findViewById(R.id.lblEstadoPedido)

        private var fabMapaRecorrido: FloatingActionButton = itemView.findViewById(R.id.fabMapaRecorrido)

        fun bind(pedido: PedidoFree) {
            lblNumeroPedido.text = pedido.id.toString()
            lblRestauranteNombrePedido.text = pedido.restaurant_id.toString()
            lblPrecioProductoPedido.text = pedido.total + " bs"
            lblEstadoPedido.text = pedido.status


            fabMapaRecorrido.setOnClickListener {
                listener.onPedidoSinAsinarItemClicked(pedido)
            }
        }
    }

    interface PedidoSinAsinarItemListener {
        fun onPedidoSinAsinarItemClicked(pedido: PedidoFree)
    }
}