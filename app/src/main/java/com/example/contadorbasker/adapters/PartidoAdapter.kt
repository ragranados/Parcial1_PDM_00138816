package com.example.contadorbasker.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadorbasker.PartidoDTO
import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.item_list.view.*

class PartidoAdapter(context: Context, val clickListener: (PartidoDTO) -> Unit) : RecyclerView.Adapter<PartidoAdapter.PartidoViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var partidos = emptyList<PartidoDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoViewHolder {
        val itemView = inflater.inflate(R.layout.item_list, parent, false)
        return PartidoViewHolder(itemView)
    }

    override fun getItemCount() = partidos.size


    override fun onBindViewHolder(holder: PartidoViewHolder, position: Int) {
        val current = partidos[position]
        Log.d("prueba",position.toString())

        holder.bind(current, clickListener)
    }

    fun setPartidos(partidos: ArrayList<PartidoDTO>){
        this.partidos = partidos
    }

    inner class PartidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PartidoDTO, clickListener: (PartidoDTO) -> Unit) = with(itemView) {
            item_view_partido.text = item.equipoUno + " vs " + item.equipoDos
            this.setOnClickListener { clickListener(item) }
        }
    }

}