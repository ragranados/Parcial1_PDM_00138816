package com.example.contadorbasker

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contadorbasker.activitys.ActividadDetallePartido
import com.example.contadorbasker.activitys.MatchCounter
import com.example.contadorbasker.adapters.PartidoAdapter
import com.example.contadorbasker.database.entitys.Partido
import com.example.contadorbasker.fragments.DetallePartido
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var partidoViewModel: PartidoViewModel

    var partidos = ArrayList<PartidoDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("xdd", "caca")

        partidoViewModel = ViewModelProviders.of(this).get(PartidoViewModel::class.java)

        //val partidooo: LiveData<List<Partido>>

        val partidooo: LiveData<List<Partido>> = partidoViewModel.partidos

        val librosObserver = Observer<List<Partido>> { partido ->
            partidos = getPartidosDTO(partidoViewModel?.partidos?.value)
            setUpView()
        }

        partidooo.observeForever(librosObserver)

        boton_nuevo_partido.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, MatchCounter::class.java), codigoDatosPartido)
        }

    }

    fun getPartidosDTO(partidos: List<Partido>?): ArrayList<PartidoDTO> {
        var partidosDTO = ArrayList<PartidoDTO>()

        if (partidos != null) {
            for (i in partidos) {
                Log.d("prueba", i.fecha)
                partidosDTO.add(PartidoDTO(i.equipoUno, i.equipoDos, i.puntajeEquipoUno, i.puntajeEquipoDos, i.fecha))
            }
        }

        return partidosDTO
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MatchCounter.codigoNuevoPartido && resultCode == Activity.RESULT_OK) {
            data?.extras?.let {
                val p: PartidoDTO = it.getParcelable(MatchCounter.DATOS_PARTIDO)

                //partidos.add(it.getParcelable(MatchCounter.DATOS_PARTIDO))

                partidoViewModel.insert(Partido(p.equipoUno, p.equipoDos, p.puntajeEquipoUno, p.puntajeEquipoDos, p.fecha))
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
