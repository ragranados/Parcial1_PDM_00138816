package com.example.contadorbasker

import android.os.Parcel
import android.os.Parcelable

class PartidoDTO(
        var equipoUno: String = "N/A",
        var equipoDos: String = "N/A",
        var puntajeEquipoUno: String = "N/A",
        var puntajeEquipoDos: String = "N/A",
        var fecha: String = "N/A"
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(equipoUno)
        parcel.writeString(equipoDos)
        parcel.writeString(puntajeEquipoUno)
        parcel.writeString(puntajeEquipoDos)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PartidoDTO> {
        override fun createFromParcel(parcel: Parcel): PartidoDTO {
            return PartidoDTO(parcel)
        }

        override fun newArray(size: Int): Array<PartidoDTO?> {
            return arrayOfNulls(size)
        }
    }
}