package com.example.contadorbasker

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contadorbasker.activitys.ActividadDetallePartido
import com.example.contadorbasker.activitys.MatchCounter
import com.example.contadorbasker.adapters.PartidoAdapter
import com.example.contadorbasker.fragments.DetallePartido
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var partidos = ArrayList<PartidoDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("xdd", "caca")

        boton_nuevo_partido.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, MatchCounter::class.java), codigoDatosPartido)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MatchCounter.codigoNuevoPartido && resultCode == Activity.RESULT_OK) {
            data?.extras?.let {
                partidos.add(it.getParcelable(MatchCounter.DATOS_PARTIDO))

                setUpView()
            }
        }


    }

    fun itemClickedPortrait(partido: PartidoDTO) {
        val infoPartido = Bundle()

        infoPartido.putParcelable("partido", partido)

        startActivity(Intent(this, ActividadDetallePartido::class.java).putExtras(infoPartido))

    }

    fun itemClickedLandScape(partido: PartidoDTO) {
        var contentFragment: DetallePartido = DetallePartido.newInstance(partido)
        changeFragment(R.id.land_detalle_partido, contentFragment)
    }

    fun setUpView() {
        var viewManager = LinearLayoutManager(this)
        lateinit var viewAdapter: PartidoAdapter


        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewAdapter = PartidoAdapter(this, { partidoItem: PartidoDTO -> itemClickedPortrait(partidoItem) })
        } else {

            viewAdapter = PartidoAdapter(this, { bookItem: PartidoDTO -> itemClickedLandScape(bookItem) })

        }

        viewAdapter.setPartidos(partidos)

        partidos.forEach { partido ->
            Log.d("partidos", partido.equipoDos)
        }

        partidos_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

    companion object {
        const val codigoDatosPartido = 1
    }
}
