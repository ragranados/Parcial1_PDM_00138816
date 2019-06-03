package com.example.contadorbasker.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contadorbasker.PartidoDTO
import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.activity_match_counter.*

class MatchCounter : AppCompatActivity() {
    var partido = PartidoDTO()
    var puntajeEquipoUno: Int = 0
    var puntajeEquipoDos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_counter)


        val intent = Intent(this@MatchCounter, New_match::class.java)

        startActivityForResult(intent, codigoNuevoPartido)
        agregarClickListeners()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == codigoNuevoPartido && resultCode == Activity.RESULT_OK) {


            data?.extras?.let {
                partido.equipoUno = it.getString(New_match.NOMBRE_EQUIPO_UNO)
                partido.equipoDos = it.getString(New_match.NOMBRE_WQUIPO_DOS)
                partido.fecha = it.getString(New_match.FECHA)
                equipo_uno.text = partido.equipoUno
                equipo_dos.text = partido.equipoDos

                titulo_partido.text = partido.equipoUno + " vs " + partido.equipoDos
            }
        }
    }

    fun replyToIntent() {
        val replyIntent = Intent()
        val reply = Bundle()

        equipo_uno.text = partido.equipoUno
        equipo_dos.text = partido.equipoDos
        partido.puntajeEquipoUno = puntajeEquipoUno.toString()
        partido.puntajeEquipoDos = puntajeEquipoDos.toString()

        reply.putParcelable(DATOS_PARTIDO, partido)
        replyIntent.putExtras(reply)

        setResult(Activity.RESULT_OK, replyIntent)
        finish()

    }

    fun agregarClickListeners() {
        bAgregarUnoEUno.setOnClickListener {
            puntajeEquipoUno += 1
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(), 1)
        }

        bAgregarDosEUno.setOnClickListener {
            puntajeEquipoUno += 2
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(), 2)
        }

        bAgregarTresEUno.setOnClickListener {
            puntajeEquipoUno += 3
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(), 3)
        }

        bAgregarUnoEDos.setOnClickListener {
            puntajeEquipoDos += 1
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(), 1)
        }

        bAgregarDosEDos.setOnClickListener {
            puntajeEquipoDos += 2
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(), 2)
        }

        bAgregarTresEDos.setOnClickListener {
            puntajeEquipoDos += 3
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(), 3)
        }

        boton_finalizar.setOnClickListener {
            replyToIntent()
        }
    }

    fun sumar(a: String, b: Int): String {
        return (a.toInt() + b).toString()
    }

    companion object {
        const val codigoNuevoPartido = 1
        const val DATOS_PARTIDO = "datos_partido"
    }
}
