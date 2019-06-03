package com.example.contadorbasker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contadorbasker.database.Repository
import com.example.contadorbasker.database.RoomDB
import com.example.contadorbasker.database.entitys.Partido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    private val database: RoomDB

    val partidos: LiveData<List<Partido>>

    init {
        database = RoomDB.getDatabase(application, viewModelScope)

        repository = Repository(database.partidoDao())

        partidos = repository.allPartidos
    }

    fun insert(partido: Partido) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(partido)
    }
}