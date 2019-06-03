package com.example.contadorbasker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contadorbasker.activitys.ActividadDetallePartido
import com.example.contadorbasker.activitys.Match_counter
import com.example.contadorbasker.adapters.PartidoAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var partidos = ArrayList<PartidoDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("xdd","caca")

        boton_nuevo_partido.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, Match_counter::class.java), codigoDatosPartido)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Match_counter.codigoNuevoPartido && resultCode == Activity.RESULT_OK) {
            data?.extras?.let {
                partidos.add(it.getParcelable(Match_counter.DATOS_PARTIDO))

                setUpView()
            }
        }


    }

    fun itemClickedPortrait(partido: PartidoDTO) {
        val infoPartido = Bundle()

        infoPartido.putParcelable("partido",partido)

        startActivity(Intent(this, ActividadDetallePartido::class.java).putExtras(infoPartido))

    }

    fun setUpView() {
        var viewManager = LinearLayoutManager(this)
        lateinit var viewAdapter: PartidoAdapter


        /*if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewAdapter = BookAdapter(this, { bookItem: LibroDTO -> itemClickedPortrait(bookItem) })
        } else {
            var contentFragment: BookDetailFragment = BookDetailFragment.newInstance(LibroDTO())
            viewAdapter = BookAdapter(this, { bookItem: LibroDTO -> itemClickedLandScape(bookItem) })
            changeFragment(R.id.land_book_detail, contentFragment)
        }*/

        viewAdapter = PartidoAdapter(this, { partidoItem: PartidoDTO -> itemClickedPortrait(partidoItem) })

        viewAdapter.setPartidos(partidos)

        partidos.forEach {partido ->
            Log.d("partidos", partido.equipoDos)
        }

        partidos_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    companion object {
        const val codigoDatosPartido = 1
    }
}
