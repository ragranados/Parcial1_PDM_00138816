package com.example.contadorbasker.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.contadorbasker.Daos.PartidoDao
import com.example.contadorbasker.database.entitys.Partido

class Repository(private val partidoDao: PartidoDao) {
    val allPartidos: LiveData<List<Partido>> = partidoDao.getAllPartidos()

    @WorkerThread
    suspend fun insert(partido: Partido) {
        partidoDao.insert(partido)
    }
}