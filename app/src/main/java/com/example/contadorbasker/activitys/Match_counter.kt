package com.example.contadorbasker.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contadorbasker.R
import kotlinx.android.synthetic.main.activity_match_counter.*
import kotlinx.android.synthetic.main.activity_new_match.*

class Match_counter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_counter)

        val intent = Intent(this@Match_counter,New_match::class.java)

        startActivityForResult(intent,codigoNuevoPartido)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == codigoNuevoPartido && resultCode == Activity.RESULT_OK){
            data?.extras?.let {
                equipo_uno.text = it.getString(New_match.NOMBRE_EQUIPO_UNO)
            }

        }

    }

    fun replyToIntent(){
        val replyIntent = Intent()
        val reply = Bundle()

        //reply.putString(NOMBRE_EQUIPO_UNO,nombre_aquipo_uno.toString())

    }

    companion object {
        const val codigoNuevoPartido = 1
    }
}
