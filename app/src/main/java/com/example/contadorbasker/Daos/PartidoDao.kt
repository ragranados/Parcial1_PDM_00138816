package com.example.contadorbasker.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.contadorbasker.database.entitys.Partido

@Dao
interface PartidoDao {

    @Insert
    suspend fun insert(partido: Partido)

    @Query("SELECT * FROM partido_table ORDER BY fecha ASC")
    fun getAllPartidos():LiveData<List<Partido>>
}