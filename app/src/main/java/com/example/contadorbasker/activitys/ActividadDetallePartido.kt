package com.example.contadorbasker.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import androidx.fragment.app.Fragment
import com.example.contadorbasker.PartidoDTO
import com.example.contadorbasker.R
import com.example.contadorbasker.fragments.DetallePartido

class ActividadDetallePartido : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_detalle_partido)


        var partidoInfo: PartidoDTO = intent?.extras?.getParcelable("partido") ?: PartidoDTO()

        initActivity(partidoInfo)


    }

    fun initActivity(partido: PartidoDTO) {
        var fragment = DetallePartido.newInstance(partido)

        changeFragment(R.id.content, fragment)
    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }
}
