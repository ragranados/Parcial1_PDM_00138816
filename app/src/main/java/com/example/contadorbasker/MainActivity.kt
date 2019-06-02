package com.example.contadorbasker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contadorbasker.activitys.Match_counter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton_nuevo_partido.setOnClickListener{
            startActivity(Intent(this@MainActivity,Match_counter::class.java))
        }

    }
}
