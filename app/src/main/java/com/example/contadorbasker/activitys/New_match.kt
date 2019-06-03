package com.example.contadorbasker.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.activity_new_match.*
import java.text.SimpleDateFormat
import java.util.*

class New_match : AppCompatActivity() {

    val Calendar : Calendar = java.util.Calendar.getInstance()
    val format = SimpleDateFormat("dd/MM/yyyy h:mm a")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_match)

        boton_crear_partido.setOnClickListener { replyToIntent() }
    }

    fun replyToIntent() {
        val replyIntent = Intent()
        val reply = Bundle()

        reply.putString(NOMBRE_EQUIPO_UNO, nombre_equipo_uno.text.toString())
        reply.putString(NOMBRE_WQUIPO_DOS, nombre_equipo_dos.text.toString())
        reply.putString(FECHA, "Fecha: "+format.format(Calendar.time))

        replyIntent.putExtras(reply)

        setResult(Activity.RESULT_OK, replyIntent)

        finish()

    }

    companion object {
        const val NOMBRE_EQUIPO_UNO = "nombre_quipo_uno"
        const val NOMBRE_WQUIPO_DOS = "nombre_equipo_2"
        const val FECHA = "fecha"
    }

}
