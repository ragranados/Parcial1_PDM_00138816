package com.example.contadorbasker.database

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contadorbasker.Daos.PartidoDao
import com.example.contadorbasker.database.entitys.Partido
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Partido::class], version = 1)
public abstract class RoomDB : RoomDatabase() {
    abstract fun partidoDao(): PartidoDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): RoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java,
                        "Partido_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}