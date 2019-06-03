package com.example.contadorbasker.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partido_table")
data class Partido(
        val equipoUno: String,
        val equipoDos: String,
        val puntajeEquipoUno: String,
        val puntajeEquipoDos: String,
        val fecha: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}