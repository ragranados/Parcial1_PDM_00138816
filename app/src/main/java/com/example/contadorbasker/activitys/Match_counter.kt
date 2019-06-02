package com.example.contadorbasker.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.contadorbasker.PartidoDTO
import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.activity_match_counter.*
import kotlinx.android.synthetic.main.activity_new_match.*

class Match_counter : AppCompatActivity() {
    val partido = PartidoDTO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_counter)



        val intent = Intent(this@Match_counter,New_match::class.java)

        startActivityForResult(intent,codigoNuevoPartido)
        agregarClickListeners()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == codigoNuevoPartido && resultCode == Activity.RESULT_OK){
            data?.extras?.let {
                equipo_uno.text = it.getString(New_match.NOMBRE_EQUIPO_UNO)
                equipo_dos.text = it.getString(New_match.NOMBRE_WQUIPO_DOS)
                Log.d("fecha", it.getString(New_match.FECHA))
            }
        }
    }

    fun replyToIntent(){
        val replyIntent = Intent()
        val reply = Bundle()

        //reply.putString(NOMBRE_EQUIPO_UNO,nombre_aquipo_uno.toString())

    }

    fun agregarClickListeners(){
        bAgregarUnoEUno.setOnClickListener {
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(),1)
        }

        bAgregarDosEUno.setOnClickListener{
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(),2)
        }

        bAgregarTresEUno.setOnClickListener {
            puntaje_equipo_uno.text = sumar(puntaje_equipo_uno.text.toString(),3)
        }

        bAgregarUnoEDos.setOnClickListener {
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(),1)
        }

        bAgregarDosEDos.setOnClickListener {
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(),2)
        }

        bAgregarTresEDos.setOnClickListener {
            puntaje_equipo_dos.text = sumar(puntaje_equipo_dos.text.toString(),3)
        }
    }

    fun sumar(a: String, b: Int):String{
        return (a.toInt()+b).toString()
    }

    companion object {
        const val codigoNuevoPartido = 1
    }
}
