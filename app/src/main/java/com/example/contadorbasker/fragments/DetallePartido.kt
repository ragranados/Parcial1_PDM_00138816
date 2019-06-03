package com.example.contadorbasker.fragments


import android.os.Bundle
import android.provider.Telephony
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contadorbasker.PartidoDTO

import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.fragment_detalle_partido.view.*

class DetallePartido : Fragment() {
    private var partido = PartidoDTO()

    private lateinit var fragmentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_detalle_partido, container, false)

        bindData()

        return fragmentView
    }

    fun bindData() {
        fragmentView.titulo.text = partido.equipoUno + " vs " + partido.equipoDos
        fragmentView.puntaje_equipo_uno.text = partido.equipoUno + ": " + partido.puntajeEquipoUno + " puntos"
        fragmentView.puntaje_equipo_dos.text = partido.equipoDos + ": " + partido.puntajeEquipoDos + " puntos"
        fragmentView.fecha.text = partido.fecha
    }


    companion object {
        @JvmStatic
        fun newInstance(partido: PartidoDTO): DetallePartido {
            val newFragment = DetallePartido()

            newFragment.partido = partido

            return newFragment
        }

    }
}
