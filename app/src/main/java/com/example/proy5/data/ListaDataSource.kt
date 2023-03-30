package com.example.proy5.data

import android.util.Log
import com.example.proy5.entidad.Data
import com.example.proy5.entidad.Producto
import com.example.proy5.entidad.SimpleMensaje
import com.example.proy5.network.ConexionApi
import com.example.proy5.entidad.Result

class ListaDataSource {

    suspend fun listarProductos(): Result<Data> {
        try {
            Log.d("DEBUG","ListaDataSource antes de llamar servicio")
            val resultado = ConexionApi.retrofitService.getProductos()
            Log.d("MSG",resultado.toString())
            return Result.Exito(Data(resultado))
        } catch (ex: Exception) {
            ex.printStackTrace()
            val error = SimpleMensaje("0001",ex.toString())
            return Result.Error(error)
        }
    }
}